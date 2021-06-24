package com.example.data.source.remote

import com.example.data.model.game.ApiResponse
import com.example.data.model.game.Game
import com.example.data.source.remote.model.ArticleDto
import com.example.data.source.remote.model.ResponseWrapperDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataSource {
    @GET("everything")
    suspend fun getArticles(
        @Query("q") query: String = "google",
        @Query("from") from: String = "2021-06-20",
        @Query("to") to: String = "2021-06-23",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("pageSize") pageSize: Int = 25,
        @Query("page") page: Int = 0,
    ): ResponseWrapperDto<List<ArticleDto>>

}