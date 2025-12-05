package com.example.aiapp.item.audiorecord

import java.io.File

interface AudioRecorder{

    fun start(outFile: File)
    fun stop()


}