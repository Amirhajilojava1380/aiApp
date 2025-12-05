package com.example.aiapp.data.api

import com.example.aiapp.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface SocketService {
    fun connect(url: String)
    fun disconnect()
    fun send(message: String)
    val messages: Flow<ChatMessage>

}