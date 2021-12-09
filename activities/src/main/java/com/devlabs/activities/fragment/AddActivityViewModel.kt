package com.devlabs.activities.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.activities.BaseViewModel
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.GetActivityUseCase
import com.devlabs.domain.usecase.StartActivityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddActivityViewModel(
    private val getActivityUseCase: GetActivityUseCase,
    private val startActivityUseCase: StartActivityUseCase
) : BaseViewModel() {

    private companion object {
        const val RANDOM_TYPE = "RANDOM"
    }

    private val _activityLiveData = MutableStateFlow<ResultWrapper<Activity>>(ResultWrapper.InitialState())
    val activityLiveData: StateFlow<ResultWrapper<Activity>>
        get() = _activityLiveData

    fun getActivityByType(type: String?) = launch {
        if (type.equals(RANDOM_TYPE, ignoreCase = true)) {
            getActivityUseCase.execute(null).collect {
                _activityLiveData.value = it
            }
        } else {
            getActivityUseCase.execute(type).collect {
                _activityLiveData.value = it
            }
        }
    }

    fun startActivity() {
        startActivityUseCase.execute()
    }
}