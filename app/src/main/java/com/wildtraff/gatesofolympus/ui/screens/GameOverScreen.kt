package com.wildtraff.gatesofolympus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wildtraff.gatesofolympus.R
import com.wildtraff.gatesofolympus.ui.BrightBlueButton
import com.wildtraff.gatesofolympus.ui.GoldenYellowButton

@Composable
fun GameOverScreen(
    stars: List<Boolean>,
    score: Int,
    onRepeat: () -> Unit,
    onExit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.game_over_frame),
            contentDescription = "Game Over Frame",
            modifier = Modifier
                .width(300.dp)
                .height(460.dp) // або трохи більше
                .align(Alignment.Center)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(top = 200.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = "ZEUS' WRATH HAS\nSTRUCK YOU!",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                stars.forEach { star ->
                    val starRes = if (star) R.drawable.star_gold else R.drawable.star_grey
                    Image(
                        painter = painterResource(id = starRes),
                        contentDescription = "Star",
                        modifier = Modifier
                            .size(36.dp)
                            .padding(horizontal = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = score.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(220.dp)
                    .clipToBounds(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.zeus_angry_cropped),
                    contentDescription = "Zeus",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                BrightBlueButton("REPEAT", onClick = onRepeat)
            }

            Spacer(modifier = Modifier.height(10.dp))

            GoldenYellowButton("Exit", onClick = onExit)
        }
    }
}


@Preview
@Composable
fun GameOverScreenView() {
    GameOverScreen(
        stars = listOf(true, false, false),
        score = 0,
        onRepeat = { },
        onExit = { })
}