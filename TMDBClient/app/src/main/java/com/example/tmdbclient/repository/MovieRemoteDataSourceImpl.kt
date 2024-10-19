package com.example.tmdbclient.repository

import com.example.tmdbclient.api.TMDBService
import com.example.tmdbclient.data.model.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey: String): MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}