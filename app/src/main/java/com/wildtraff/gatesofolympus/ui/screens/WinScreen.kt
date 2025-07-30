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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wildtraff.gatesofolympus.R
import com.wildtraff.gatesofolympus.domain.models.GameLevel
import com.wildtraff.gatesofolympus.ui.BrightBlueButton
import com.wildtraff.gatesofolympus.ui.GoldenYellowButton

@Composable
fun WinScreen(
    level: GameLevel,
    onNextClick: (level: GameLevel) -> Unit = {},
    onExitClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAAAFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.win_frame),
            contentDescription = "Win Frame",
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
                text = "YOU HAVE PROVEN",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "YOUR WORTH!",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row{
                level.stars.forEach { star ->
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
                text = "${level.score}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
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
                    painter = painterResource(id = R.drawable.zeus_happy_cropped),
                    contentDescription = "Zeus",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                BrightBlueButton("NEXT", onClick = {onNextClick(level)})
            }

            Spacer(modifier = Modifier.height(10.dp))

            GoldenYellowButton("Exit", onClick = onExitClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WinScreenPreview() {
    WinScreen(GameLevel(score = 10, stars = listOf(true, true, false)))
}
