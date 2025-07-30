package com.wildtraff.gatesofolympus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wildtraff.gatesofolympus.R

@Composable
fun SettingsScreen(
    soundEnabled: Boolean = false,
    musicEnabled: Boolean = false,
    onSoundToggle: (Boolean) -> Unit,
    onMusicToggle: (Boolean) -> Unit,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x88000000))
            .clickable(onClick = onClose),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.settings_bg),
                contentDescription = "Settings Background",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.TopCenter)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OlympusBanner(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 24.dp)
                        .height(70.dp)
                        .width(200.dp),
                    text = "SETTINGS"
                )

                SettingRow(
                    iconResId = if (soundEnabled) R.drawable.sound_loud else R.drawable.sound_muffled,
                    label = "SOUND",
                    checked = soundEnabled,
                    onToggle = onSoundToggle
                )

                Spacer(modifier = Modifier.height(10.dp))

                SettingRow(
                    iconResId = if (musicEnabled) R.drawable.music_on else R.drawable.music_off,
                    label = "MUSIC",
                    checked = musicEnabled,
                    onToggle = onMusicToggle
                )
            }

            IconButton(
                onClick = onClose,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(40.dp)
            ) {
                Image(painter = painterResource(R.drawable.exit_button), contentDescription = "Settings",
                    modifier = Modifier.size(40.dp))

            }
        }
    }
}

@Composable
fun SettingRow(
    iconResId: Int = 1,
    label: String = "",
    checked: Boolean = false,
    onToggle: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(250.dp)
            .padding(horizontal = 20.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onToggle
        )
    }
}


@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        soundEnabled = true,
        musicEnabled = false,
        onSoundToggle = {},
        onMusicToggle = {},
        onClose = {}
    )
}