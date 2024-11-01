package com.example.tmdbclient.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbclient.data.dao.MovieDao
import com.example.tmdbclient.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}