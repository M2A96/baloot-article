package com.example.data.source.remote

import com.example.data.model.game.ApiResponse
import com.example.data.model.game.Game
import com.example.data.source.remote.model.ResponseWrapperDto
import retrofit2.http.GET

interface GameDataSource {
    @GET("srv/game/get")
    suspend fun getGames(): ApiResponse

}