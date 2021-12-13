package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AbandonActivityUseCase {
    suspend fun execute(activity: Activity): Flow<ResultWrapper<Unit>>
}