package com.example.aiapp.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aiapp.R

@Composable
fun TelegramVoiceMessageUI(
    isFromMe: Boolean,
    isPlaying: Boolean,
    currentTime: String,
    onPlayPauseClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .background(
                color = if (isFromMe) Color(0xFFDCF8C6) else Color(0xFFF6F5F5),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onPlayPauseClick) {
            Icon(
                painter = painterResource(
                    if (isPlaying) R.drawable.stop_svgrepo_com else R.drawable.play_svgrepo_com
                ),
                contentDescription = null,
                tint = Color(0xFF4A4A4A),
                modifier = Modifier.size(26.dp)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(50))
        )

        Text(
            text = currentTime,
            color = Color(0xFF4A4A4A),
            modifier = Modifier.padding(start = 8.dp),
        )
    }
}
