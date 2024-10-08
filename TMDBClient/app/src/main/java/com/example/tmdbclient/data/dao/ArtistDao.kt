package com.example.tmdbclient.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @Query("DELETE FROM Artist")
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM Artist")
    suspend fun getArtists(): List<Artist>
}