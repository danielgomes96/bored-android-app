package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class GetActivityUseCaseImpl(
    private val activityRepository: ActivityRepository
) : GetActivityUseCase {
    override suspend fun execute(type: String?) : Flow<ResultWrapper<Activity>> {
        return activityRepository.getActivity(type)
    }
}