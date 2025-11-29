package com.example.biblewatch.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for daily Bible verse
 */
@Serializable
data class DailyVerse(
    @SerialName("verse")
    val verse: VerseData
)

@Serializable
data class VerseData(
    @SerialName("details")
    val details: VerseDetails,
    @SerialName("notice")
    val notice: String,
    @SerialName("version")
    val version: String? = "WEB"
)

@Serializable
data class VerseDetails(
    @SerialName("text")
    val text: String,
    @SerialName("reference")
    val reference: String,
    @SerialName("version")
    val version: String,
    @SerialName("verseurl")
    val verseUrl: String
)

