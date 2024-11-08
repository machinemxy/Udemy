package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.domain.MovieRepository
import com.example.tmdbclient.repository.MovieCacheDataSource
import com.example.tmdbclient.repository.MovieLocalDataSource
import com.example.tmdbclient.repository.MovieRemoteDataSource
import com.example.tmdbclient.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource)
    }
}