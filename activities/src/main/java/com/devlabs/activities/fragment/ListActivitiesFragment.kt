package com.devlabs.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.activities.ActivitiesAdapter
import com.devlabs.activities.ListActivitiesViewModel
import com.devlabs.activities.R
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivitiesFragment : Fragment() {

    private lateinit var rvActivities: RecyclerView
    private val viewModel by viewModel<ListActivitiesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_activities, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvActivities = view.findViewById(R.id.activities_recycler_view)
        rvActivities.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.getStartedActivities()
        setupObserver()
    }

    private fun setupObserver() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getActivitiesLiveData.collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        val adapter = ActivitiesAdapter(result.value, ::handleItemClicked)
                        rvActivities.adapter = adapter
                    }
                }
            }
        }


    }

    private fun handleItemClicked(activity: Activity) {

    }
}