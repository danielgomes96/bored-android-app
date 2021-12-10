package com.devlabs.data.repository

import ActivityDomainMapper
import com.devlabs.data.database.ActivitiesDao
import com.devlabs.data.dto.DTOActivity
import com.devlabs.data.mapper.ActivityLocalMapper
import com.devlabs.data.mapper.ActivityRemoteMapper
import com.devlabs.data.service.BoredAPI
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class ActivityRepositoryImpl(
    private val boredAPI: BoredAPI,
    private val activityDao: ActivitiesDao
): ActivityRepository {

    override suspend fun getActivity(type: String?): Flow<ResultWrapper<Activity>> = flow  {
        emit(ResultWrapper.Loading)
        runCatching {
            boredAPI.getActivity(type)
        }.onSuccess {
            emit(ResultWrapper.Success(ActivityRemoteMapper().transform(it.body() ?: DTOActivity("", "", 0, 0f, "", "", 0.1f))))
        }.onFailure { throwable ->
            when (throwable) {
                is IOException -> emit(ResultWrapper.NetworkError)
                is HttpException -> emit(ResultWrapper.GenericError(throwable.code(), throwable.message))
                else -> emit(ResultWrapper.GenericError(null, throwable.message))
            }
        }
    }

    override suspend fun startActivity(activity: Activity): Flow<ResultWrapper<Unit>> = flow {
        emit(ResultWrapper.Loading)
        runCatching {
            activityDao.insertActivity(
                ActivityLocalMapper().transform(activity)
            )
        }.onSuccess {
            emit(ResultWrapper.Success(Unit))
        }.onFailure { throwable ->
            when (throwable) {
                is Exception -> emit(ResultWrapper.GenericError(null, throwable.message))
            }
        }
    }

    override suspend fun getStartedActivities(): Flow<ResultWrapper<List<Activity>>> = flow {
        emit(ResultWrapper.Loading)
        runCatching {
            activityDao.getActivities()
        }.onSuccess {
            emit(ResultWrapper.Success(ActivityDomainMapper().transform(it)))
        }.onFailure { throwable ->
            when (throwable) {
                is Exception -> emit(ResultWrapper.GenericError(null, throwable.message))
            }
        }
    }
}