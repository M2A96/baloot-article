package com.example.data.repository

import arrow.core.Either
import com.example.data.model.Error
import com.example.mapper.ErrorMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository(private val errorMapper: ErrorMapper) {
    protected suspend fun <T : Any> safeApiCall(call: suspend () -> T): Either<Error, T> {
        return withContext(Dispatchers.IO) {
            getResult(call)
        }
    }

    protected suspend fun <T : Any> getResult(call: suspend () -> T): Either<Error, T> {
        return try {
            Either.right(call.invoke())
        } catch (t: Throwable) {
            Either.left(errorMapper.getError(t))
        }
    }
}
