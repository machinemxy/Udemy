package com.example.tmdbclient.domain

import com.example.tmdbclient.data.model.Movie

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getMovies()
}