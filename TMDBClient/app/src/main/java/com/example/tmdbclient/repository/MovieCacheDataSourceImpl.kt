package com.example.tmdbclient.repository

import com.example.tmdbclient.data.model.Movie

class MovieCacheDataSourceImpl: MovieCacheDataSource {
    private var movieList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList = ArrayList(movies)
    }
}