package com.example.tmdbclient.repository

import com.example.tmdbclient.data.model.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache(): List<Movie>
    suspend fun saveMoviesToCache(movies: List<Movie>)
}