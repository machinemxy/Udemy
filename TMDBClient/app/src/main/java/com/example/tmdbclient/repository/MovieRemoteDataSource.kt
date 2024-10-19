package com.example.tmdbclient.repository

import com.example.tmdbclient.data.model.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}