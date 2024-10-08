package com.example.tmdbclient.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbclient.data.dao.ArtistDao
import com.example.tmdbclient.data.dao.MovieDao
import com.example.tmdbclient.data.dao.TvShowDao
import com.example.tmdbclient.data.model.Artist
import com.example.tmdbclient.data.model.Movie
import com.example.tmdbclient.data.model.TvShow

@Database(entities = [Movie::class, TvShow::class, Artist::class], version = 1, exportSchema = false)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun artistDao(): ArtistDao
}