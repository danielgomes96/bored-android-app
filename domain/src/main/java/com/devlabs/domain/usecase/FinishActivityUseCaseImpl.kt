package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class FinishActivityUseCaseImpl(
    private val activityRepository: ActivityRepository
) : FinishActivityUseCase {

    override suspend fun execute(activity: Activity): Flow<ResultWrapper<Unit>> {
        return activityRepository.finishActivity(activity)
    }

}