package com.example.aiapp.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.io.File

class FileAdapter {
    @ToJson
    fun toJson(file: File?): String? = file?.absolutePath

    @FromJson
    fun fromJson(str: String?): File? = str?.let { File(it) }
}
