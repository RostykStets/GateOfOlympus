package com.wildtraff.gatesofolympus.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wildtraff.gatesofolympus.ui.screens.GameOverScreen
import com.wildtraff.gatesofolympus.ui.screens.GameScreen
import com.wildtraff.gatesofolympus.ui.screens.LevelsScreen
import com.wildtraff.gatesofolympus.ui.screens.MainMenuScreen
import com.wildtraff.gatesofolympus.ui.screens.PauseMenuScreen
import com.wildtraff.gatesofolympus.ui.screens.SettingsScreen
import com.wildtraff.gatesofolympus.ui.screens.WinScreen
import com.wildtraff.gatesofolympus.application.viewmodels.GameLevelViewModel

@Composable
fun AppNavigationGraph(
    navController: NavHostController
) {

    val showSettings = remember { mutableStateOf(false) }
    val showLoseScreen = remember { mutableStateOf(false) }
    val showWinScreen = remember { mutableStateOf(false) }
    val showPause = remember { mutableStateOf(false) }
    val isSoundEnabled = remember { mutableStateOf(true) }
    val isMusicEnabled = remember { mutableStateOf(true) }

    val gameLevelViewModel: GameLevelViewModel = hiltViewModel()

    val currentLevel = gameLevelViewModel.level.collectAsState()

    LaunchedEffect(currentLevel) {
        currentLevel.let {
            gameLevelViewModel.getFirstLockedGameLevelOrNull()
        }
    }


    NavHost(
        navController = navController,
        startDestination = ScreenRoute.MainScreen.route,
        builder = {
            composable(ScreenRoute.MainScreen.route) {
                val context = LocalContext.current
                MainMenuScreen(
                    onPlay = {
                        if (currentLevel.value == null) {
                                showToast(context, message = "You've passed all levels!")
                        } else {
                            navController.navigate("${ScreenRoute.GameViewScreen.route}/${currentLevel.value!!.id}")
                        }
                    }, onLevels = {
                        navController.navigate(ScreenRoute.LevelsScreen.route) {
                            popUpTo(ScreenRoute.MainScreen.route) { inclusive = false }
                        }
                    },

                    onSettings = { showSettings.value = true }
                )
            }

            composable(ScreenRoute.LevelsScreen.route) {
                LevelsScreen(onOpenLevel = { gameLevel ->
                    val gameLevelId = gameLevel.id
                    navController.navigate("${ScreenRoute.GameViewScreen.route}/$gameLevelId") {
                        popUpTo(ScreenRoute.LevelsScreen.route) { inclusive = false }
                    }
                })
            }
            composable(
                route = "${ScreenRoute.GameViewScreen.route}/{levelId}",
                arguments = listOf(navArgument("levelId") { type = NavType.IntType })
            ) { backStackEntry ->
                val levelId = backStackEntry.arguments?.getInt("levelId") ?: 1

                GameScreen(
                    levelId,
                    onPauseClick = { showPause.value = true },
                    onSettingsClick = { showSettings.value = true },
                    onWin = { level ->
                        if (currentLevel.value!!.score < level.score)
                            gameLevelViewModel.upsertGameLevel(level)
                        showWinScreen.value = true
                    },
                    onLose = { level ->
                        showLoseScreen.value = true
                    })
            }
        }

    )
    if (showSettings.value) {
        SettingsScreen(
            soundEnabled = isSoundEnabled.value,
            musicEnabled = isMusicEnabled.value,
            onSoundToggle = { isSoundEnabled.value = !isSoundEnabled.value },
            onMusicToggle = { isMusicEnabled.value = !isMusicEnabled.value },
            onClose = { showSettings.value = false }
        )
    }

    if (showPause.value) {
        PauseMenuScreen(
            onContinue = { showPause.value = false },
            {},
            onMenu = {
                navController.popBackStack()
                showPause.value = false
                navController.navigate(ScreenRoute.MainScreen.route)
            }
        )
    }

    if (showLoseScreen.value) {
        GameOverScreen(
            currentLevel.value!!.stars,
            score = currentLevel.value!!.score,
            {
                showLoseScreen.value = false
                navController.navigate("${ScreenRoute.GameViewScreen.route}/${currentLevel.value!!.id}")
            },
            {
                navController.popBackStack()
                showLoseScreen.value = false
                navController.navigate(ScreenRoute.MainScreen.route)
            })
    }

    if (showWinScreen.value) {
        val context = LocalContext.current
        WinScreen(
            currentLevel.value!!,
            onNextClick = {
                    level ->
                gameLevelViewModel.getGameLevelByIdOrNull(level.id)
                if (currentLevel.value == null)
                    showToast(context, message = "You've passed all levels!")
                else{
                    navController.popBackStack()
                        showWinScreen.value = false
                    navController.navigate("${ScreenRoute.GameViewScreen.route}/${currentLevel.value!!.id}")
                }

            },
            onExitClick = {
                navController.popBackStack()
                showWinScreen.value = false
                navController.navigate(ScreenRoute.MainScreen.route)
            })
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(
        context, message,
        Toast.LENGTH_SHORT
    ).show()
}
