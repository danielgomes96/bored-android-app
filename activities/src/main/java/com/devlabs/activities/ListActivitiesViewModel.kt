package com.devlabs.activities

import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.GetStartedActivitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListActivitiesViewModel(
    private val getStartedActivitiesUseCase: GetStartedActivitiesUseCase
) : BaseViewModel() {

    private val _getActivitiesLiveData = MutableStateFlow<ResultWrapper<List<Activity>>>(ResultWrapper.InitialState())
    val getActivitiesLiveData: StateFlow<ResultWrapper<List<Activity>>>
        get() = _getActivitiesLiveData

    fun getStartedActivities() = launch {
        getStartedActivitiesUseCase.execute().collect {
            _getActivitiesLiveData.value = it
        }
    }
}