package com.example.tmdbclient.repository

import com.example.tmdbclient.data.model.Movie
import com.example.tmdbclient.domain.MovieRepository

class FakeMovieRepository: MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return listOf(
            Movie(1, "overview1", "posterPath1", "date1", "title1"),
            Movie(2, "overview2", "posterPath2", "date2", "title2")
        )
    }

    override suspend fun updateMovies(): List<Movie> {
        return listOf(
            Movie(3, "overview3", "posterPath3", "date3", "title3"),
            Movie(4, "overview4", "posterPath4", "date4", "title4")
        )
    }
}