package com.example.aiapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aiapp.view.GreetingChat
import com.example.aiapp.viewmodel.MainViewmodel

@Composable
fun Nav(navController: NavHostController, mainViewModel: MainViewmodel ) {

    NavHost(navController = navController, startDestination = Screen.ScreenMain.route){

            composable(
                route = Screen.ScreenMain.route,
            ){
                GreetingChat(mainViewmodel = mainViewModel)
            }

    }
}