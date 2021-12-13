package com.devlabs.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.activities.ActivitiesAdapter
import com.devlabs.activities.AddActivityViewModel
import com.devlabs.activities.R
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ProgressStatus
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ListActivitiesFragment : Fragment() {

    private lateinit var rvActivities: RecyclerView
    private lateinit var tvEmptyView: TextView
    private lateinit var progressBar: ProgressBar
    private val viewModel by viewModel<AddActivityViewModel>()
    private val observer = Observer<ResultWrapper<List<Activity>>> { handleResponse(it) }

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
        tvEmptyView = view.findViewById(R.id.fragment_empty_view)
        progressBar = view.findViewById(R.id.fragment_progress_bar)
        rvActivities.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.getStartedActivities()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getActivitiesLiveData.observe(viewLifecycleOwner, observer)
    }

    private fun handleResponse(result: ResultWrapper<List<Activity>>?) {
        when (result) {
            is ResultWrapper.Success -> {
                tvEmptyView.visibility = View.GONE
                progressBar.visibility = View.GONE
                val adapter = ActivitiesAdapter(result.value, ::handleItemClicked, requireContext())
                rvActivities.adapter = adapter
            }
            is ResultWrapper.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is ResultWrapper.Empty -> {
                progressBar.visibility = View.GONE
                tvEmptyView.visibility = View.VISIBLE
            }
            else -> {

            }
        }
    }

    private fun handleItemClicked(activity: Activity) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.dialog_activity_description))
        builder.setPositiveButton(getString(R.string.dialog_activity_finish)){ dialog, which ->
            val diff: Long = Date(System.currentTimeMillis()).time - activity.startDate.time
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            activity.startDate
            activity.progressStatus = ProgressStatus.FINISHED
            viewModel.finishActivity(activity)
        }
        builder.setNegativeButton(getString(R.string.dialog_activity_abandon)){ dialog,which ->
            activity.progressStatus = ProgressStatus.ABANDONED
            viewModel.abandonActivity(activity)
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}