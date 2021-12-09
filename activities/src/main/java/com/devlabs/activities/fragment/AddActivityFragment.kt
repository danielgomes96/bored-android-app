package com.devlabs.activities.fragment

import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.devlabs.activities.R
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class AddActivityFragment : Fragment() {

    private lateinit var btGetActivities: Button
    private val addActivityViewModel by viewModel<AddActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_activity, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btGetActivities = view.findViewById(R.id.btn_get_activities)
        btGetActivities.setOnClickListener {
            addActivityViewModel.getActivityByType("charity")
        }
        setupObserver()
    }

    private fun setupObserver() {
        CoroutineScope(Dispatchers.Main).launch {
            addActivityViewModel.activityLiveData.collect {
                when (it) {
                    is ResultWrapper.Success -> {
                        Log.d("BUNDA", "Teste: ${it.value.activity}")
                    }
                    else -> {

                    }
                }
            }
        }
    }
}