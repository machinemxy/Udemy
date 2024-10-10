package com.example.tmdbclient.domain

import com.example.tmdbclient.data.model.Artist

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.updateArtists()
}