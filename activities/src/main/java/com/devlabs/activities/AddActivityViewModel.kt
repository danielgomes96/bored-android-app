package com.devlabs.activities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.core.Consts.Companion.RANDOM_TYPE
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class AddActivityViewModel(
    private val getActivityUseCase: GetActivityUseCase,
    private val startActivityUseCase: StartActivityUseCase,
    private val getStartedActivitiesUseCase: GetStartedActivitiesUseCase,
    private val finishActivityUseCase: FinishActivityUseCase,
    private val abandonActivityUseCase: AbandonActivityUseCase
) : ViewModel() {

    private val _activityLiveData = MutableLiveData<ResultWrapper<Activity>>(ResultWrapper.InitialState())
    val activityLiveData: LiveData<ResultWrapper<Activity>>
        get() = _activityLiveData

    private val _startActivityLiveData = MutableLiveData<ResultWrapper<Unit>>(ResultWrapper.InitialState())
    val startActivityLiveData: LiveData<ResultWrapper<Unit>>
        get() = _startActivityLiveData

    private val _getActivitiesLiveData = MutableLiveData<ResultWrapper<List<Activity>>>(ResultWrapper.InitialState())
    val getActivitiesLiveData: LiveData<ResultWrapper<List<Activity>>>
        get() = _getActivitiesLiveData

    private val _updateActivityProgressLiveData = MutableLiveData<ResultWrapper<Unit>>(ResultWrapper.InitialState())
    val updateActivityProgressLiveData: LiveData<ResultWrapper<Unit>>
        get() = _updateActivityProgressLiveData

    private var viewModelJob = Job()

    fun getActivityByType(type: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (type.equals(RANDOM_TYPE, ignoreCase = true)) {
                getActivityUseCase.execute(null).collect {
                    _activityLiveData.postValue(it)
                }
            } else {
                getActivityUseCase.execute(type).collect {
                    _activityLiveData.postValue(it)
                }
            }
        }
    }

    fun startActivity(activity: Activity) = CoroutineScope(Dispatchers.IO).launch {
        startActivityUseCase.execute(activity).collect {
            _startActivityLiveData.postValue(it)
        }
    }

    fun getStartedActivities() = CoroutineScope(Dispatchers.IO).launch {
        getStartedActivitiesUseCase.execute().collect {
            _getActivitiesLiveData.postValue(it)
        }
    }

    fun abandonActivity(activity: Activity, minutes: Int) = CoroutineScope(Dispatchers.IO).launch {
        abandonActivityUseCase.execute(activity, minutes).collect {
            _updateActivityProgressLiveData.postValue(it)
        }
    }

    fun finishActivity(activity: Activity, minutes: Int) = CoroutineScope(Dispatchers.IO).launch {
        finishActivityUseCase.execute(activity, minutes).collect {
            _updateActivityProgressLiveData.postValue(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}