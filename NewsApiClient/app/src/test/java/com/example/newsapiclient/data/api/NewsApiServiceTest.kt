package com.example.newsapiclient.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiServiceTest {
    private lateinit var service: NewsApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines() {
        runBlocking {
            enqueueMockResponse("newsResponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val request = server.takeRequest()
            val articles = responseBody!!.articles
            assertThat(articles.size).isEqualTo(18)
            val article = articles[0]
            assertThat(article.author).isEqualTo("KIM TONG-HYUNG")
            assertThat(article.url).isEqualTo("https://apnews.com/article/south-korea-actor-death-kim-sae-ron-a2ce58f4d63b423afb328709027429ba")
            assertThat(article.publishedAt).isEqualTo("2025-02-18T06:03:00Z")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}