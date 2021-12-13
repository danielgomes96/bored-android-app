package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class AbandonActivityUseCaseImpl(
    private val activityRepository: ActivityRepository
) : AbandonActivityUseCase {
    override suspend fun execute(activity: Activity, minutes: Int): Flow<ResultWrapper<Unit>> {
        return activityRepository.abandonActivity(activity, minutes)
    }
}