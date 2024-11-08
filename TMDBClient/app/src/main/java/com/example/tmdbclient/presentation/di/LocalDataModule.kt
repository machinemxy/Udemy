package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.data.dao.MovieDao
import com.example.tmdbclient.repository.MovieLocalDataSource
import com.example.tmdbclient.repository.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {
    @Provides
    @Singleton
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }
}