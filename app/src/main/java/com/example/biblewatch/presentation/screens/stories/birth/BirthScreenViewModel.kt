package com.example.biblewatch.presentation.screens.stories.birth

import androidx.lifecycle.ViewModel
import com.example.biblewatch.presentation.model.Sound
import com.example.biblewatch.sound.AndroidAudioPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BirthScreenViewModel @Inject constructor(
    player: AndroidAudioPlayer,
) : BaseAudioViewModel(player)

abstract class BaseAudioViewModel(
    private val player: AndroidAudioPlayer
) : ViewModel() {

    fun playSound(sound: Sound) {
        val audioRes = sound.getResourceFromSound()
        player.playFile(audioRes)
    }

    fun stopPlayer() {
        player.stopPlayer()
    }

    override fun onCleared() {
        super.onCleared()
        player.stopPlayer()
    }
}


