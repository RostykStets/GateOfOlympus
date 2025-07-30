package com.wildtraff.gatesofolympus.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wildtraff.gatesofolympus.R
import com.wildtraff.gatesofolympus.ui.screens.fontGentiumBold

@Composable
fun BrightBlueButton(text: String, fontSize: Int = 35, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .width(200.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.btn_bright_blue_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = fontGentiumBold
        )
    }
}

@Composable
fun GoldenYellowButton(
    text: String,
    fontSize: Int = 30,
    onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(65.dp)
            .width(160.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.btn_golden_yellow_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = fontGentiumBold
        )
    }
}