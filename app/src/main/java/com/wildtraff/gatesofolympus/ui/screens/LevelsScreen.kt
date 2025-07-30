package com.wildtraff.gatesofolympus.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wildtraff.gatesofolympus.R
import com.wildtraff.gatesofolympus.domain.models.GameLevel
import com.wildtraff.gatesofolympus.domain.viewmodels.GameLevelViewModel

@Composable
fun LevelsScreen(
    onOpenLevel: (GameLevel) -> Unit
) {

    val gameLevelViewModel: GameLevelViewModel = hiltViewModel()

    val levels = gameLevelViewModel.levels.collectAsState()


    LaunchedEffect(Unit) {
        gameLevelViewModel.getGameLevels()
    }


    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.levels_list_bg),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(80.dp))
                OlympusBanner(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .width(200.dp)
                        .height(50.dp),
                    text = "LEVELS"
                )
            }


            items(levels.value.chunked(3)) { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    row.forEach { level ->
                        if (level.isPassed) {
                            UnlockedLevelItem(level, onOpenLevel = onOpenLevel)
                        } else {
                            LockedLevelItem(level, onOpenLevel = onOpenLevel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UnlockedLevelItem(
    level: GameLevel,
    onOpenLevel: (GameLevel) -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box {
            Row(
                modifier = Modifier
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                level.stars.forEach { star ->
                    val resId = if (star) R.drawable.star_gold else R.drawable.star_grey
                    Image(
                        painter = painterResource(
                            id = resId
                        ),
                        contentDescription = "Stars",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

        }


        Box(modifier = Modifier.clickable(onClick = { onOpenLevel(level) })) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.level_passed_bg
                        ),
                        contentDescription = "Level ${level.id}",
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = "${level.id}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A237E),
                        modifier = Modifier.padding(top = 4.dp)
                    )

                }
                Text(
                    text = "Score: ${level.score}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A237E),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun LockedLevelItem(level: GameLevel, onOpenLevel: (GameLevel) -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.level_locked_bg),
        contentDescription = "Locked",
        modifier = Modifier
            .size(80.dp)
            .clickable(onClick = { onOpenLevel(level) })
    )
}

@Preview
@Composable
fun LevelsScreenView() {
    LevelsScreen(
        onOpenLevel = {})
}