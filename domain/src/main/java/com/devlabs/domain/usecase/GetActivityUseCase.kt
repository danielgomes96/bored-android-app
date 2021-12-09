package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface GetActivityUseCase {
    suspend fun execute(type: String): Flow<ResultWrapper<Activity>>
}