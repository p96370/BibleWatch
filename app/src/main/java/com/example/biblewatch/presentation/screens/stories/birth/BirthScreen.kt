package com.example.biblewatch.presentation.screens.stories.birth

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.biblewatch.R
import com.example.biblewatch.presentation.model.Sound
import com.example.biblewatch.presentation.story.CategoryIcon
import com.example.biblewatch.presentation.story.StoryPageData
import com.example.biblewatch.presentation.story.StoryScreenContent

@Composable
fun BirthScreen(viewModel: BirthScreenViewModel = hiltViewModel(), onBack: () -> Unit) {

    BirthScreenContent(
        playStory = { viewModel.playSound(Sound.BirthStory) },
        onBack = onBack,
        playSound = viewModel::playSound,
        stopSound = viewModel::stopPlayer,
    )
}

@Composable
private fun BirthScreenContent(
    playStory: () -> Unit, onBack: () -> Unit, playSound: (Sound) -> Unit, stopSound: () -> Unit
) {
    val birthPagesData = listOf(


        StoryPageData(
            title = "Journey to Bethlehem",
            imageRes = R.drawable.birth_1,
            interactiveIcons = listOf(
                CategoryIcon.Donkey
            )
        ),
        StoryPageData(
            title = "Arrival at the manger",
            imageRes = R.drawable.birth_2,
            interactiveIcons = listOf(
                CategoryIcon.Lamb
            )
        ),
        StoryPageData(
            title = "Magi's search for Jesus",
            imageRes = R.drawable.birth_3,
            interactiveIcons = listOf(
                CategoryIcon.Horse
            )
        ),
        StoryPageData(
            title = "Little king was born",
            imageRes = R.drawable.birth_4,
            interactiveIcons = listOf(
                CategoryIcon.Angel, CategoryIcon.Pray, CategoryIcon.Baby
            )
        ),
    )

    StoryScreenContent(
        pagesData = birthPagesData,
        onBack = onBack,
        playSound = playSound,
        playStory = playStory,
        stopSound = stopSound
    )
}

@Preview
@Composable
private fun PreviewTest() {
    BirthScreenContent({}, {}, {}, {})
}
