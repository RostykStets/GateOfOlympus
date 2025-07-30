package com.wildtraff.gatesofolympus.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wildtraff.gatesofolympus.R
import com.wildtraff.gatesofolympus.ui.BrightBlueButton
import com.wildtraff.gatesofolympus.ui.GoldenYellowButton

@Composable
fun MainMenuScreen(
    onPlay: () -> Unit,
    onLevels: () -> Unit,
    onSettings: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.god),
                    contentDescription = "Zeus Main Screen",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(200.dp)
                        .height(250.dp)
                )

                OlympusBanner(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BrightBlueButton("PLAY", onClick = onPlay)

                Column(verticalArrangement = Arrangement.Center) {
                    Spacer(modifier = Modifier.height(30.dp))
                    GoldenYellowButton(text = "Levels", onClick = onLevels)
                    //GoldenYellowButton(text = "Settings", onClick = {showSettings.value = true})
                    GoldenYellowButton(text = "Settings", onClick = onSettings)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun OlympusBanner(
    modifier: Modifier = Modifier,
    text: String = "GATES OF\nOLYMPUS"
) {
    Box(
        modifier = modifier
            .width(260.dp)
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val offsetX = width * 0.1f // "зріз" боків

            val path = Path().apply {
                moveTo(offsetX, 0f)
                lineTo(width - offsetX, 0f)
                lineTo(width, height / 2)
                lineTo(width - offsetX, height)
                lineTo(offsetX, height)
                lineTo(0f, height / 2)
                close()
            }

            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF3F51B5), Color(0xFF673AB7))
                )
            )

            drawPath(
                path = path,
                color = Color(0xFF000080),
                style = Stroke(width = 15f)
            )
        }
        
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            style = TextStyle(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFF176), Color(0xFFFFA000))
                ),
                shadow = Shadow(Color.Black, Offset(2f, 2f), 4f)
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainMenuScreenPreview() {
    MainMenuScreen( onPlay = {}, onLevels = {}, onSettings = {})
}