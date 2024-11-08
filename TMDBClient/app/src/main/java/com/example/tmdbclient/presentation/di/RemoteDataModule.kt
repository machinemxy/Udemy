package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.api.TMDBService
import com.example.tmdbclient.repository.MovieRemoteDataSource
import com.example.tmdbclient.repository.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {
    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService, apiKey)
    }
}