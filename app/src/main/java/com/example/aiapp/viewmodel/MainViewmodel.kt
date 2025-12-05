package com.example.aiapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiapp.model.ChatMessage
import com.example.aiapp.repository.Repository
import com.example.aiapp.utils.NetWorkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val repo: Repository
) : ViewModel() {


    val messages = mutableStateListOf<ChatMessage>()

    private val _socketState = MutableStateFlow<NetWorkResult<ChatMessage>>(NetWorkResult.Lod())
    val socketState = _socketState.asStateFlow()

    fun connect(url: String) {
        repo.remote.connect(url)
        viewModelScope.launch {
            repo.remote.observeSocketState().collect { msg ->
                _socketState.value = NetWorkResult.Success(msg)
                messages.add(msg)
            }
        }
    }

    fun send(text: String) {
        viewModelScope.launch {
            try {
                repo.remote.send(text)
            } catch (e: Exception) {
                _socketState.value = NetWorkResult.Error(e.message ?: "unknown error")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        repo.remote.disconnect()
    }



}