package com.wildtraff.gatesofolympus.ui

sealed class ScreenRoute(val route: String){
    object MainScreen: ScreenRoute("main_screen")
    object LevelsScreen: ScreenRoute("levels_screen")
    object GameViewScreen: ScreenRoute("game_screen")
}