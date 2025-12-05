package com.example.aiapp.data.remotedatasource

import com.example.aiapp.data.api.ApiService
import com.example.aiapp.data.api.SocketService
import jakarta.inject.Inject

class RemoteDataSource @Inject constructor(
    private val socket: SocketService,
    private val api: ApiService
){

    fun connect(url: String) = socket.connect(url)

    fun observeSocketState() = socket.messages

    suspend fun send(text: String) = socket.send(text)

    fun disconnect() = socket.disconnect()

}