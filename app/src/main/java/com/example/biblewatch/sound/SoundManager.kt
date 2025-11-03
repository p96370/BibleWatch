package com.example.biblewatch.sound

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

interface AudioPlayer {
    fun playFile(file: File): Boolean
    fun playFile(@RawRes audioRes: Int): Boolean
    fun stopPlayer()
}

@Singleton
class AndroidAudioPlayer @Inject constructor(
    @ApplicationContext private val context: Context,
) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile(file: File): Boolean {
        return playUsingPlayer {
            MediaPlayer().apply {
                this.isLooping = isLooping
                setDataSource(file.path)
                prepare()
            }
        }
    }

    override fun playFile(@RawRes audioRes: Int): Boolean {
        return playUsingPlayer {
            MediaPlayer.create(context, audioRes).apply {
                this.isLooping = isLooping
            }
        }
    }

    override fun stopPlayer() {
        player?.stop()
        player?.release()
        player = null
    }

    private inline fun playUsingPlayer(playerProvider: () -> MediaPlayer): Boolean {
        return try {
            stopPlayer()
            player = playerProvider().apply {
                setVolume(1f, 1f) // sets internal player volume for left and right speaker
                start()
            }
            true
        } catch (e: Exception) {
            println("Failed to play file")
            e.printStackTrace()
            false
        }
    }
}
