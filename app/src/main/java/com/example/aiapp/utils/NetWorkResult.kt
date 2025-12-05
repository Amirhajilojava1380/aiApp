package com.example.aiapp.utils


sealed class NetWorkResult < T > (val data: T? = null , val message: String? = null) {

    class Success<T>(data :T?) : NetWorkResult<T>(data = data)
    class Error  <T>(message: String?):NetWorkResult<T>(message = message)
    class Lod    <T>:NetWorkResult<T>()

}