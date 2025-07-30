package com.wildtraff.gatesofolympus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wildtraff.gatesofolympus.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedLightningStrike(
    modifier: Modifier = Modifier,
    play: Boolean = false,
    onAnimationEnd: () -> Unit = {}
) {
    val frameResources = listOf(
        R.drawable.lightning_strike_low,
        R.drawable.lightning_strike_mid,
        R.drawable.lightning_strike_high,
        R.drawable.lightning_strike_max
    )

    val frameIndex = remember { mutableIntStateOf(0) }
    val isPlaying = rememberUpdatedState(play)

    LaunchedEffect(isPlaying.value) {
        if (isPlaying.value) {
            for (i in frameResources.indices) {
                frameIndex.intValue = i
                delay(70L)
            }
            delay(200L)
            onAnimationEnd()
        } else {
            frameIndex.intValue = 0
        }
    }

    Image(
        painter = painterResource(id = frameResources[frameIndex.intValue]),
        contentDescription = "Lightning",
        modifier = modifier
            .fillMaxHeight(0.5f)
    )
}
