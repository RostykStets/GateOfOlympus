package com.wildtraff.gatesofolympus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.wildtraff.gatesofolympus.ui.theme.GatesOfOlympusTheme
import com.wildtraff.gatesofolympus.ui.AppNavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GatesOfOlympusTheme {
                val navController = rememberNavController()
                AppNavigationGraph(navController)
            }
        }
    }
}