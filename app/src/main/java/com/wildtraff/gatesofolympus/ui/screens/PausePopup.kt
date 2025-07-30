package com.wildtraff.gatesofolympus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wildtraff.gatesofolympus.R
import com.wildtraff.gatesofolympus.ui.BrightBlueButton
import com.wildtraff.gatesofolympus.ui.GoldenYellowButton

@Composable
fun PauseMenuScreen(
    onContinue: () -> Unit,
    onRestart: () -> Unit,
    onMenu: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x88000000))
            .clickable(onClick = onContinue),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
            , contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.continue_bg),
                contentDescription = "Win Frame",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.TopCenter)
            )
            Column(
                modifier = Modifier
                    .width(250.dp)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                BrightBlueButton(text = "Continue", fontSize = 30, onClick = onContinue)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GoldenYellowButton("Restart", fontSize = 25, onClick = onRestart)
                    GoldenYellowButton("Menu", fontSize = 25, onClick = onMenu)
                }
            }
        }
    }
}

@Preview
@Composable
fun PauseMenuPreview() {
    PauseMenuScreen(
        onContinue = {},
        onRestart = {},
        onMenu = {}
    )
}
