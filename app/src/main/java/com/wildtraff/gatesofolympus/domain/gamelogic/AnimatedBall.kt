package com.wildtraff.gatesofolympus.domain.gamelogic

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

@Composable
fun AnimatedBall(
    modifier: Modifier = Modifier,
    animationSpeed: Long = 300L,
    isGameOver: Boolean = false,
    onBrightnessUpdate: (BrightnessStage) -> Unit = {}
) {
    val brightnessStages = listOf(
        BrightnessStage.NOT_SHINING,
        BrightnessStage.BIT_SHINING,
        BrightnessStage.BIT_MORE,
        BrightnessStage.DIM,
        BrightnessStage.MID,
        BrightnessStage.ALMOST,
        BrightnessStage.BRIGHT,
        BrightnessStage.ALMOST,
        BrightnessStage.MID,
        BrightnessStage.DIM,
        BrightnessStage.BIT_MORE,
        BrightnessStage.BIT_SHINING
    )

    val currentIndex = remember { mutableIntStateOf(0) }
    val currentStage = brightnessStages[currentIndex.intValue]

    LaunchedEffect(animationSpeed, isGameOver) {
        while (!isGameOver) {
            delay(animationSpeed)
            currentIndex.intValue = (currentIndex.intValue + 1) % brightnessStages.size
            onBrightnessUpdate(brightnessStages[currentIndex.intValue])
        }
    }

    Image(
        painter = painterResource(id = currentStage.drawableRes),
        contentDescription = null,
        modifier = modifier
    )
}