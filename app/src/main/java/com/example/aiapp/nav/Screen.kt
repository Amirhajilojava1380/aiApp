package com.example.aiapp.nav

sealed class Screen(val route: String) {

    data object  ScreenMain : Screen("ScreenMain")

}