package com.example.newsapiclient.data.repository.dataSourceImpl

import com.example.newsapiclient.data.api.NewsApiService
import com.example.newsapiclient.data.model.APIResponse
import com.example.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
): NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
        return newsApiService.getTopHeadlines(country, page)
    }
}