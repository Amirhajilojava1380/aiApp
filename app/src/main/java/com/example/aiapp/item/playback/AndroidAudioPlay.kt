package com.example.aiapp.item.playback

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlay(
    private val context: Context
) : AudioPlay {

    private var player: MediaPlayer? = null

    override fun start(file: File) {
        MediaPlayer.create(context,file.toUri()).apply {
            player = this
            start()
            Log.d("isPlay","start")
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
        Log.d("isPlay","stop")
    }


}