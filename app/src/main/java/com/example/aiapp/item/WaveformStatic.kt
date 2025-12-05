package com.example.aiapp.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WaveformStatic() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(20) {
            Box(
                Modifier
                    .width(3.dp)
                    .height((8..22).random().dp)
                    .background(Color.Gray, RoundedCornerShape(50))
            )

            Spacer(Modifier.width(2.dp))
        }
    }
}
