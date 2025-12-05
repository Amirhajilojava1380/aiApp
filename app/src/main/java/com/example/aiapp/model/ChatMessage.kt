package com.example.aiapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatMessage(
    val text: String? = null,
    val isFromMe: Boolean = false,
    val imageUri: String? = null,
    val fileVoice: String? = null
)