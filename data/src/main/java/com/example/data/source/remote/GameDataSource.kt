package com.example.data.source.remote

import com.example.data.model.game.ApiResponse
import com.example.data.model.game.Game
import com.example.data.source.remote.model.ResponseWrapperDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GameDataSource {
    @GET("everything")
    suspend fun getGames(
        @Query("q")  query: String = "google",
        @Query("from") from: String = "2021-06-23",
        @Query("to")  to: String = "2021-06-23",
        @Query("sortBy")  sortBy: String = "popularity",
    ): ApiResponse

}