package com.wildtraff.gatesofolympus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.wildtraff.gatesofolympus.application.gamelogic.GameState
import com.wildtraff.gatesofolympus.R
import com.wildtraff.gatesofolympus.application.gamelogic.TapEngine
import com.wildtraff.gatesofolympus.application.gamelogic.AnimatedBall
import com.wildtraff.gatesofolympus.application.gamelogic.BrightnessStage
import com.wildtraff.gatesofolympus.application.models.GameLevel
import com.wildtraff.gatesofolympus.application.viewmodels.GameLevelViewModel

val fontGentiumBold = FontFamily(
    Font(R.font.gentium_book_plus_bold, FontWeight.Bold)
)

@Composable
fun GameScreen(
    levelId: Int = 1,
    onPauseClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onWin: (GameLevel) -> Unit = {},
    onLose: (GameLevel) -> Unit = {}
) {
    val gameLevelViewModel: GameLevelViewModel = hiltViewModel()
    val level = gameLevelViewModel.level.collectAsState()

    LaunchedEffect(levelId) {
        gameLevelViewModel.getGameLevelByIdOrNull(levelId)
    }


    val lives = listOf(false, false, false)
    val state = remember { mutableStateOf(GameState()) }
    val currentBrightnessStage = remember { mutableStateOf(BrightnessStage.DIM) }
    val scoreState = remember { mutableIntStateOf(0) }
    val livesState = remember { lives.toMutableStateList() }
    val showLightning = remember { mutableStateOf(false) }
    val gameBG = remember { mutableIntStateOf(R.drawable.game_bg) }

    val tapEngine = remember {
        TapEngine(
            onHit = {
                showLightning.value = true
                scoreState.intValue += 1
                TapEngine({}, {}).updateConditions(scoreState.intValue)

                if (scoreState.intValue == level.value!!.scoreLimit) {
                    state.value = state.value.copy(isGameOver = true)
                    val updatedLevel = GameLevel(
                        level.value!!.id,
                        score = scoreState.intValue,
                        stars = listOf(true, true, true),
                        level.value!!.scoreLimit,
                        true
                    )
                    gameBG.intValue = R.drawable.win_bg
                    onWin(updatedLevel)
                }
            },
            onMiss = {
                showLightning.value = true
                if (livesState.contains(false)) {
                    val index = livesState.indexOfFirst { !it }
                    livesState[index] = true
                }
                if (livesState.last()) {
                    state.value = state.value.copy(isGameOver = true)
                    val gameObjectScore = (level.value!!.scoreLimit.toFloat() / 3.0f).toInt()
                    val starsGained = when {
                        scoreState.intValue >= gameObjectScore * 2 && scoreState.intValue < level.value!!.scoreLimit -> listOf(
                            true,
                            true,
                            false
                        )

                        scoreState.intValue >= level.value!!.scoreLimit -> listOf(true, true, true)
                        scoreState.intValue >= gameObjectScore -> listOf(true, false, false)
                        else -> listOf(false, false, false)
                    }

                    val updatedLevel = GameLevel(
                        level.value!!.id,
                        score = scoreState.intValue,
                        stars = starsGained,
                        level.value!!.scoreLimit,
                        level.value!!.isPassed
                    )
                    onLose(updatedLevel)
                }
            }
        )
    }

    GameLoop(state = state.value) { newBrightness ->
        state.value = state.value.copy(brightnessLevel = newBrightness)
    }

    Box {
        if (showLightning.value) {
            AnimatedLightningStrike(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .align(Alignment.TopCenter)
                    .zIndex(2f),
                play = true,
                onAnimationEnd = { showLightning.value = false }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Transparent),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        ) {
            Image(
                painter = painterResource(id = gameBG.intValue),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                TopBar(
                    score = scoreState,
                    lives = livesState,
                    onPauseClick = onPauseClick,
                    onSettingsClick = onSettingsClick,
                    onRestartClick = {
                        scoreState.intValue = 0
                        for (i in livesState.indices) {
                            livesState[i] = false
                        }
                        state.value = GameState()
                        currentBrightnessStage.value = BrightnessStage.DIM
                    }
                )
                Spacer(modifier = Modifier.weight(1f))

                AnimatedBall(
                    modifier = Modifier
                        .size(300.dp)
                        .zIndex(1f),
                    animationSpeed = calculateSpeed(scoreState.intValue),
                    isGameOver = state.value.isGameOver,
                    onBrightnessUpdate = {
                        currentBrightnessStage.value = it
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .height(80.dp)
                        .width(200.dp)
                        .clickable {
                            tapEngine.updateStage(currentBrightnessStage.value)
                            tapEngine.handleTap()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(R.drawable.btn_bright_blue_bg),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        "STOP",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = fontGentiumBold
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
fun TopBar(
    score: MutableIntState,
    lives: List<Boolean>,
    onPauseClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onRestartClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .width(350.dp)
            .wrapContentHeight()
            .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .width(350.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(70.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.score_bg),
                    contentDescription = "Score Background",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .matchParentSize()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 6.dp)

                )
                {
                    IconButton(modifier = Modifier.size(40.dp), onClick = onRestartClick) {
                        Image(
                            painter = painterResource(id = R.drawable.restart_btn),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(70.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "SCORE:",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontGentiumBold
                        )

                        Text(
                            text = "${score.intValue}",
                            fontSize = 30.sp,
                            color = Color.Yellow,
                            fontFamily = fontGentiumBold
                        )
                    }

                    Spacer(modifier = Modifier.width(40.dp))

                    IconButton(modifier = Modifier.size(30.dp), onClick = onPauseClick) {
                        Image(
                            painter = painterResource(R.drawable.pause_btn),
                            contentDescription = "Pause",
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(modifier = Modifier.size(30.dp), onClick = onSettingsClick) {
                        Image(
                            painter = painterResource(R.drawable.settings_btn),
                            contentDescription = "Settings",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 4.dp,
                        color = Color(0xFFFFC107),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {

                Row(
                    modifier = Modifier
                        .background(Color(0xFF1E3A5F), RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    lives.forEach { isBroken ->
                        val resId = if (isBroken) R.drawable.broken_heart else R.drawable.heart
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                                .padding(horizontal = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}