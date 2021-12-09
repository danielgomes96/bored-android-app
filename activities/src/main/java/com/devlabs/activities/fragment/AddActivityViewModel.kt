package com.devlabs.activities.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.activities.BaseViewModel
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.GetActivityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddActivityViewModel(
    private val getActivityUseCase: GetActivityUseCase
) : BaseViewModel() {

    private val _activityLiveData = MutableStateFlow<ResultWrapper<Activity>>(ResultWrapper.InitialState())
    val activityLiveData: StateFlow<ResultWrapper<Activity>>
        get() = _activityLiveData

    fun getActivityByType(type: String) = launch {
        getActivityUseCase.execute(type).collect {
            _activityLiveData.value = it
        }
    }
}