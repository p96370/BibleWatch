package com.example.biblewatch.presentation.story

// Represents a single page in the story
data class StoryPageData(
    val title: String,
    val imageRes: Int,
    val interactiveIcons: List<CategoryIcon> = emptyList()
)