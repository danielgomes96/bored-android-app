package com.devlabs.activities

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + viewModelJob

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}