package com.example.biblewatch.data.remote

import com.example.biblewatch.data.model.DailyVerse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BibleApiService @Inject constructor(
    private val httpClient: HttpClient
) {
    companion object {
        private const val BASE_URL = "https://beta.ourmanna.com/api/v1"
    }

    suspend fun getDailyVerse(): Result<DailyVerse> {
        return try {
            val response: DailyVerse = httpClient.get("$BASE_URL/get/?format=json").body()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getRandomVerse(): Result<DailyVerse> {
        return try {
            val response: DailyVerse = httpClient.get("$BASE_URL/get/?format=json&order=random").body()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

