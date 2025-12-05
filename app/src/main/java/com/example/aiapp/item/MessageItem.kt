package com.example.aiapp.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.aiapp.model.ChatMessage
import kotlinx.coroutines.delay
import kotlin.text.iterator

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MessageItem(message: ChatMessage, isLast: Boolean) {

    val arrangement = if (message.isFromMe) Arrangement.End else Arrangement.Start

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalArrangement = arrangement
    ) {

        when {
            message.text!!.isNotEmpty() && message.imageUri == null && message.fileVoice == null -> {
                Box(
                    modifier = Modifier
                        .background(
                            color = if (message.isFromMe) Color(0x00FFFFFF) else Color(0xFFF6F5F5),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(12.dp)
                ) {

                    Column {
                        var currentText by remember { mutableStateOf("") }
                        var animated by remember(message.text) { mutableStateOf(false) }

                        if (message.isFromMe && isLast) {
                            LaunchedEffect(isLast) {
                                if (isLast && !animated) {
                                    for (char in message.text) {
                                        currentText += char
                                        delay(30)
                                    }
                                }
                                animated = true
                            }
                            Text(text = currentText)
                        } else {
                            Text(text = message.text)
                        }

                    }

                }
            }

            message.imageUri != null && message.text.isEmpty() -> {
                Column {
                    // اگر عکس هست نمایش بده
                    GlideImage(
                        model = message.imageUri,
                        contentDescription = "Message Image",
                        modifier = Modifier
                            .width(200.dp)
                            .height(250.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            message.imageUri != null && message.text.isNotEmpty() -> {

                Column {


                    GlideImage(
                        model = message.imageUri,
                        contentDescription = "Message Image",
                        modifier = Modifier
                            .width(200.dp)
                            .height(250.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (message.isFromMe) Color(0x00FFFFFF) else Color(
                                    0xFFF6F5F5
                                ),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(12.dp)
                    ) {

                        Column {

                        }

                    }

                }

            }


        }

    }
}


