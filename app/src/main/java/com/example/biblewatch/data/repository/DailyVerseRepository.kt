package com.example.biblewatch.data.repository

import com.example.biblewatch.data.model.DailyVerse
import com.example.biblewatch.data.remote.BibleApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyVerseRepository @Inject constructor(
    private val bibleApiService: BibleApiService
) {
    fun getDailyVerse(): Flow<Result<DailyVerse>> = flow {
        emit(bibleApiService.getDailyVerse())
    }

    fun getRandomVerse(): Flow<Result<DailyVerse>> = flow {
        emit(bibleApiService.getRandomVerse())
    }
}

