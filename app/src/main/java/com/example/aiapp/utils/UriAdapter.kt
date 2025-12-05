package com.example.aiapp.utils

import android.net.Uri
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import androidx.core.net.toUri

class UriAdapter {

    @ToJson
    fun toJson(uri: Uri?): String? {
        return uri?.toString()
    }

    @FromJson
    fun fromJson(uri: String?): Uri? {
        return uri?.let { it.toUri() }
    }
}
