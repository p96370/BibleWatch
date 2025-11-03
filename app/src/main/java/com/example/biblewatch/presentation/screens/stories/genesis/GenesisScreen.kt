package com.example.biblewatch.presentation.screens.stories.genesis

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.biblewatch.R
import com.example.biblewatch.presentation.model.Sound
import com.example.biblewatch.presentation.story.CategoryIcon
import com.example.biblewatch.presentation.story.StoryPageData
import com.example.biblewatch.presentation.story.StoryScreenContent

@Composable
fun GenesisScreen(viewModel: GenesisScreenViewModel = hiltViewModel(), onBack: () -> Unit) {
    GenesisScreenContent(
        onBack,
        viewModel::playSound,
        { viewModel.playSound(Sound.GenesisStory) },
        viewModel::stopPlayer
    )
}

@Composable
private fun GenesisScreenContent(
    onBack: () -> Unit, playSound: (Sound) -> Unit, playStory: () -> Unit, stopStory: () -> Unit
) {
    val birthPagesData = listOf(
        StoryPageData(
            title = "Day 1: Light out of Darkness",
            imageRes = R.drawable.genesis1,
            interactiveIcons = listOf(
                CategoryIcon.Fire, CategoryIcon.Thunder
            )
        ),
        StoryPageData(
            title = "Day 2: Seas and oceans",
            imageRes = R.drawable.genesis2,
            interactiveIcons =
                listOf(
                    CategoryIcon.Water, CategoryIcon.Waterfall
                )
        ),
        StoryPageData(
            title = "Day 5: Animals", imageRes = R.drawable.genesis5, interactiveIcons = listOf(
                CategoryIcon.Animals
            )
        ),
        StoryPageData(
            title = "Day 6: Adam and Eve",
            imageRes = R.drawable.genesis6,
            interactiveIcons = listOf(
                CategoryIcon.AdamEve,
            )
        ),
    )

    StoryScreenContent(
        pagesData = birthPagesData,
        onBack = onBack,
        playSound = playSound,
        playStory = playStory,
        stopSound = stopStory
    )
}

@Preview
@Composable
fun PreviewTest() {
    GenesisScreenContent({}, {}, {}) { }
}