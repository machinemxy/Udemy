package com.example.tmdbclient.domain

import com.example.tmdbclient.data.model.TvShow

interface TvShowRepository {
    suspend fun getTvShows(): List<TvShow>?
    suspend fun updateTvShows(): List<TvShow>?
}