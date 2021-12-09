package com.devlabs.data.repository

import com.devlabs.data.dto.DTOActivity
import com.devlabs.data.mapper.ActivityMapper
import com.devlabs.data.service.BoredAPI
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ActivityRepositoryImpl(
    private val boredAPI: BoredAPI
): ActivityRepository {

    override suspend fun getActivity(type: String): Flow<ResultWrapper<Activity>> = flow  {
        emit(ResultWrapper.Loading)
        runCatching {
            boredAPI.getActivity(type)
        }.onSuccess {
            emit(ResultWrapper.Success(ActivityMapper().transform(it.body() ?: DTOActivity("", "", 0, 0f, "", "", 0.1f))))
        }.onFailure { throwable ->
            when (throwable) {
                is IOException -> emit(ResultWrapper.NetworkError)
                is HttpException -> emit(ResultWrapper.GenericError(throwable.code(), throwable.message))
                else -> emit(ResultWrapper.GenericError(null, throwable.message))
            }
        }
    }
}