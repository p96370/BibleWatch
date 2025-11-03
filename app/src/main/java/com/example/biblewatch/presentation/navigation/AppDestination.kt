package com.example.biblewatch.presentation.navigation

import com.example.biblewatch.R

sealed class AppDestination(
    val route: String,
    val label: String,
    val imageRes: Int,
) {
    object Home : AppDestination("home_root", "Stories", R.drawable.kids_icon)
    object Study : AppDestination("study_root", "Play", R.drawable.book_icon)

    companion object {
        val bottomBarItems = listOf( Home, Study)
    }
}

object Birth {
    const val route = "birth"
}
object Genesis {
    const val route = "genesis"
}

object Draw {
    const val route = "draw"
}

object AnimalsGame {
    const val route = "animals_game"
}

