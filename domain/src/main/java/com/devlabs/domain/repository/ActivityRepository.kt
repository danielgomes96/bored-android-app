package com.devlabs.domain.repository

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    suspend fun getActivity(type: String?): Flow<ResultWrapper<Activity>>
    suspend fun startActivity(activity: Activity)
}