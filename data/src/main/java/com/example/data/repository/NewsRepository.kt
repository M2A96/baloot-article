package com.example.data.repository

import android.util.Log
import arrow.core.Either
import com.example.data.di.qualifier.Concrete
import com.example.data.model.Error
import com.example.data.source.remote.NewsDataSource
import com.example.data.source.remote.model.ArticleDto
import com.example.mapper.ErrorMapper
import javax.inject.Inject

class NewsRepository @Inject constructor(
    errorMapper: ErrorMapper,
    @Concrete private val newsDataSource: NewsDataSource
) : BaseRepository(errorMapper) {

    suspend fun getArticles(page: Int): Either<Error, List<ArticleDto>> {
        Log.d("TAG", "getArticles: page number is $page")
        return safeApiCall {
            newsDataSource.getArticles(page = page)
        }.map {
            it.data
        }
    }
}