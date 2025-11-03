package com.example.biblewatch.presentation.screens.study

import com.example.biblewatch.presentation.screens.stories.birth.BaseAudioViewModel
import com.example.biblewatch.sound.AndroidAudioPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimalsGameScreenViewModel @Inject constructor(
    player: AndroidAudioPlayer,
) : BaseAudioViewModel(player)
