package com.example.aiapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.aiapp.nav.Nav
import com.example.aiapp.nav.Screen
import com.example.aiapp.ui.theme.AiAppTheme
import com.example.aiapp.utils.requestPermissions
import com.example.aiapp.utils.setSoftInputMode
import com.example.aiapp.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        requestPermissions(this)
        setSoftInputMode(window)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AiAppTheme {
                val navHostController  = rememberNavController()
                LaunchedEffect(Unit) {
                    navHostController.navigate(Screen.ScreenMain.route)
                }
                Nav(navController = navHostController , mainViewModel = mainViewModel)
            }
        }
    }
}


