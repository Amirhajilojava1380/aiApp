package com.example.aiapp.data.websocket

import com.example.aiapp.data.api.SocketService
import com.example.aiapp.model.ChatMessage
import com.squareup.moshi.Moshi
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatWebSocket @Inject constructor(
    private val moshi: Moshi
) : WebSocketListener(), SocketService {

    private val client = OkHttpClient()
    private var socket: WebSocket? = null
    private val adapter = moshi.adapter(ChatMessage::class.java)

    private val _onMessage = MutableSharedFlow<ChatMessage>()
    override val messages: Flow<ChatMessage> = _onMessage.asSharedFlow()

    override fun connect(url: String) {
        val req = Request.Builder().url(url).build()
        socket = client.newWebSocket(req, this)
    }

    override fun disconnect() {
        socket?.close(1000, "Closed")
    }

    override fun send(text: String) {
        socket?.send(text)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val msg = try {
                adapter.fromJson(text)
            } catch (e: Exception) {
                null
            }

            msg?.let { _onMessage.emit(it) }
        }
    }
}
