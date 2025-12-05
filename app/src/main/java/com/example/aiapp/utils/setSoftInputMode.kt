package com.example.aiapp.utils

import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowCompat

fun setSoftInputMode(window: Window){
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    WindowCompat.setDecorFitsSystemWindows(window, false)
}