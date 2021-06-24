package com.example.data.repository

import arrow.core.Either
import com.example.data.di.qualifier.Concrete
import com.example.data.model.Error
import com.example.data.model.game.Game
import com.example.data.source.remote.GameDataSource
import com.example.mapper.ErrorMapper
import javax.inject.Inject

class GameRepository @Inject constructor(
    errorMapper: ErrorMapper,
    @Concrete private val gameDataSource: GameDataSource
) : BaseRepository(errorMapper) {

    suspend fun getGames(): Either<Error, List<Game>> {
        return safeApiCall {
            gameDataSource.getGames()
        }.map {
            it.result
        }
    }
}