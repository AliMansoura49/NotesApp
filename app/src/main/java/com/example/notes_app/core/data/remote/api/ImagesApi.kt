package com.example.notes_app.core.data.remote.api


import com.example.notes_app.core.data.remote.dto.ImageListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {
    @GET("/api/")
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("key") apiKey: String = API_KEY
    ):ImageListDto?
    companion object{
        const val BASE_URL = "https://pixabay.com"
        const val API_KEY = "45470821-ea7b1d878d3fc48df1355131b"
    }
}