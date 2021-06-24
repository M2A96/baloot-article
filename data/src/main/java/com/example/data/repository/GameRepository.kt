package com.example.data.repository

import arrow.core.Either
import com.example.data.di.qualifier.Concrete
import com.example.data.model.Error
import com.example.data.source.remote.NewsDataSource
import com.example.data.source.remote.model.ArticleDto
import com.example.mapper.ErrorMapper
import javax.inject.Inject

class GameRepository @Inject constructor(
    errorMapper: ErrorMapper,
    @Concrete private val newsDataSource: NewsDataSource
) : BaseRepository(errorMapper) {

    suspend fun getArticles(): Either<Error, List<ArticleDto>> {
        return safeApiCall {
            newsDataSource.getArticles()
        }.map {
            it.data
        }
    }
}