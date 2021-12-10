package com.devlabs.activities
import com.devlabs.core.Consts.Companion.RANDOM_TYPE
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

    private val _activityLiveData = MutableStateFlow<ResultWrapper<Activity>>(ResultWrapper.InitialState())
    val activityLiveData: StateFlow<ResultWrapper<Activity>>
        get() = _activityLiveData

    private val _startActivityLiveData = MutableStateFlow<ResultWrapper<Unit>>(ResultWrapper.InitialState())
    val startActivityLiveData: StateFlow<ResultWrapper<Unit>>
        get() = _startActivityLiveData

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

    fun startActivity(activity: Activity) = launch {
        startActivityUseCase.execute(activity).collect {
            _startActivityLiveData.value = it
        }
    }
}