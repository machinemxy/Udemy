package com.mxy.retrofitdemo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET(value = "/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET(value = "/albums")
    suspend fun getAlbumsByUserId(@Query("userId") userId: Int): Response<Albums>
}
