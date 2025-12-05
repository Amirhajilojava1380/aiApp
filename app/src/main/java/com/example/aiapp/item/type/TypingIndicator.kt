package com.example.aiapp.item.type

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TypingIndicator() {
    val dots = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            dots.value = "."
            delay(300)
            dots.value = ".."
            delay(300)
            dots.value = "..."
            delay(300)
        }
    }

    Text(
        text = dots.value,
        modifier = Modifier.padding(8.dp).size(6.dp)
    )
}
