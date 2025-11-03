package com.example.biblewatch.presentation.model

import com.example.biblewatch.R

enum class Sound {
    Birth1Donkey,
    Birth2Lamb,
    Birth3Horse,
    Birth4Sheep,
    Pray,
    Birth4Angels,
    Birth4Baby,
    BirthStory,
    GenesisStory,
    Thunder,
    Water,
    Waterfall,
    Fire;
    fun getResourceFromSound(): Int {
        return when (this) {
            Birth1Donkey -> R.raw.birth1donkey
            Birth2Lamb -> R.raw.birth2lamb
            Birth3Horse -> R.raw.birth3horse
            Birth4Sheep -> R.raw.birth4sheep
            Pray -> R.raw.birth4humans
            Birth4Angels -> R.raw.birth4angels
            BirthStory -> R.raw.birth1story
            Birth4Baby -> R.raw.birth4baby
            GenesisStory -> R.raw.genesisstory
            Fire -> R.raw.fire
            Thunder -> R.raw.thunder
            Water -> R.raw.water
            Waterfall -> R.raw.waterfall
        }
    }
}