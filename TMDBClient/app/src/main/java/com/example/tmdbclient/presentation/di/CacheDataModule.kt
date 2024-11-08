package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.repository.MovieCacheDataSource
import com.example.tmdbclient.repository.MovieCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Provides
    @Singleton
    fun provideMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }
}