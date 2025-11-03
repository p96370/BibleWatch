package com.example.biblewatch.presentation.story

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.biblewatch.R
import com.example.biblewatch.presentation.model.Sound

sealed class CategoryIcon(val iconRes: Int, val tint: Color, val sound: Sound, val size: Dp = 60.dp, val padding: PaddingValues = PaddingValues(0.dp), val videoName: String = "") {
    data object Donkey : CategoryIcon(R.drawable.donkey, Color.Companion.Magenta, Sound.Birth1Donkey, padding = PaddingValues(top = 400.dp, end = 200.dp), size = 150.dp)
    data object Lamb : CategoryIcon(R.drawable.sheep_icon, Color.Companion.White, Sound.Birth2Lamb, padding = PaddingValues(top = 300.dp), size = 200.dp)
    data object Horse : CategoryIcon(R.drawable.horse, Color.Companion.Red, Sound.Birth3Horse, padding = PaddingValues(top = 400.dp), size = 200.dp)
    data object Animals : CategoryIcon(R.drawable.animal_3_icon, Color.Companion.Yellow, Sound.Birth2Lamb, padding = PaddingValues(top = 400.dp), size = 300.dp, videoName = "animals_move")
    data object Angel : CategoryIcon(R.drawable.angel_icon, Color.Companion.Cyan, Sound.Birth4Angels, padding = PaddingValues(bottom = 500.dp), size = 150.dp)
    data object Pray : CategoryIcon(R.drawable.pray_icon, Color.Companion.Green, Sound.Pray, padding = PaddingValues(top = 225.dp, start = 250.dp), size = 120.dp)
    data object Baby : CategoryIcon(R.drawable.baby_icon, Color(0xFFE1E1E1), Sound.Birth4Baby, padding = PaddingValues(top = 350.dp, end = 75.dp), size = 150.dp)
    data object Fire : CategoryIcon(R.drawable.fire, Color.Companion.Red, Sound.Fire, size = 340.dp, videoName = "earth_move")
    data object Thunder : CategoryIcon(R.drawable.thunder, Color.Companion.Cyan, Sound.Thunder, padding = PaddingValues(bottom = 500.dp), videoName = "lightning_video")
    data object Water : CategoryIcon(R.drawable.water, Color(0xFF00B9FF), Sound.Water, padding = PaddingValues(top = 200.dp), size = 200.dp, videoName = "inside_water")
    data object Waterfall : CategoryIcon(R.drawable.waterfall, Color.Companion.Cyan, Sound.Waterfall, padding = PaddingValues(bottom = 350.dp), size = 200.dp, videoName = "waterfall_video")
    data object AdamEve : CategoryIcon(R.drawable.pray_icon, Color.Companion.Red, Sound.Pray, padding = PaddingValues(top = 550.dp), size = 240.dp, videoName = "adam_and_eve_video")
}

fun CategoryIcon.getLabel() = when (this) {
    is CategoryIcon.Angel -> "Angel"
    is CategoryIcon.Animals -> "Animals"
    is CategoryIcon.Baby -> "Baby"
    is CategoryIcon.Waterfall -> "Waterfall"
    is CategoryIcon.Thunder -> "Lightning"
    is CategoryIcon.Fire -> "Fire"
    else -> ""
}
