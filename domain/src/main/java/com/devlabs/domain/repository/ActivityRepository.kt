package com.devlabs.domain.repository

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    suspend fun getActivity(type: String?): Flow<ResultWrapper<Activity>>
    suspend fun startActivity(activity: Activity): Flow<ResultWrapper<Unit>>
    suspend fun getStartedActivities(): Flow<ResultWrapper<List<Activity>>>
    suspend fun finishActivity(activity: Activity): Flow<ResultWrapper<Unit>>
    suspend fun abandonActivity(activity: Activity): Flow<ResultWrapper<Unit>>
}