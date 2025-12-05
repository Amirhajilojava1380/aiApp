package com.example.aiapp.item.playback

import java.io.File

interface AudioPlay {

    fun start(file: File)

    fun stop()


}