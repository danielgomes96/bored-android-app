package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class GetStartedActivitiesUseCaseImpl(
    private val activityRepository: ActivityRepository
): GetStartedActivitiesUseCase {
    override suspend fun execute(): Flow<ResultWrapper<List<Activity>>> {
        return activityRepository.getStartedActivities()
    }
}