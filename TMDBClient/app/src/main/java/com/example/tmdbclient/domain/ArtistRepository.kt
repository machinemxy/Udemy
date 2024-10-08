package com.example.tmdbclient.domain

import com.example.tmdbclient.data.model.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?
}