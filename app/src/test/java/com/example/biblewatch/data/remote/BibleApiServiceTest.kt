package com.example.biblewatch.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BibleApiServiceTest {

    private lateinit var mockEngine: MockEngine
    private lateinit var httpClient: HttpClient
    private lateinit var bibleApiService: BibleApiService

    @Before
    fun setup() {
        mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel(mockDailyVerseResponse),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }

        bibleApiService = BibleApiService(httpClient)
    }

    @Test
    fun `getDailyVerse returns success with valid data`() = runTest {
        val result = bibleApiService.getDailyVerse()

        assertTrue(result.isSuccess)
        val verse = result.getOrNull()
        assertEquals("John 3:16", verse?.verse?.details?.reference)
        assertTrue(verse?.verse?.details?.text?.contains("God so loved") == true)
    }

    @Test
    fun `getRandomVerse returns success with valid data`() = runTest {
        val result = bibleApiService.getRandomVerse()

        assertTrue(result.isSuccess)
        val verse = result.getOrNull()
        assertEquals("John 3:16", verse?.verse?.details?.reference)
    }

    companion object {
        private const val mockDailyVerseResponse = """
        {
            "verse": {
                "details": {
                    "text": "For God so loved the world, that he gave his only begotten Son, that whoever believes in him should not perish, but have eternal life.",
                    "reference": "John 3:16",
                    "version": "WEB",
                    "verseurl": "https://ourmanna.com/verses/john-3-16"
                },
                "notice": "API powered by OurManna.com",
                "version": "WEB"
            }
        }
        """
    }
}

