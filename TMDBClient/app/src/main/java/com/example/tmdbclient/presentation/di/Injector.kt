package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.presentation.di.movie.MovieSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
}