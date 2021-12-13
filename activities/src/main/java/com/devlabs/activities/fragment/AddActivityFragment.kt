package com.devlabs.activities.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.devlabs.activities.AddActivityViewModel
import com.devlabs.activities.R
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddActivityFragment : Fragment() {

    private lateinit var btGetActivities: Button
    private lateinit var spinnerTypes: Spinner
    private lateinit var progressBar: ProgressBar
    private val addActivityViewModel by viewModel<AddActivityViewModel>()
    private var showBottomSheet: Boolean = false
    private val observer = Observer<ResultWrapper<Activity>> { handleResponse(it) }

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
        spinnerTypes = view.findViewById(R.id.spinner_types)
        progressBar = view.findViewById(R.id.progress_bar)
        btGetActivities.setOnClickListener {
            showBottomSheet = true
            addActivityViewModel.getActivityByType(spinnerTypes.selectedItem.toString().toLowerCase())
        }
        setupSpinner()
        setupObserver()
    }

    private fun setupSpinner() {
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.filters)
        )
        spinnerTypes.adapter = arrayAdapter
    }

    private fun setupObserver() {
        addActivityViewModel.activityLiveData.observe(viewLifecycleOwner, observer)
    }

    private fun handleResponse(result: ResultWrapper<Activity>?) {
        when (result) {
            is ResultWrapper.Loading -> {
                showLoading()
            }
            is ResultWrapper.Success -> {
                dismissLoading()
                showBottomSheetDialog(result.value)
            }
            else -> {

            }
        }
    }

    private fun dismissLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun showBottomSheetDialog(activity: Activity) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)
        val tvTitle = bottomSheetDialog.findViewById<TextView>(R.id.activity_title)
        val btStart = bottomSheetDialog.findViewById<TextView>(R.id.activity_txt_start)
        tvTitle?.text = activity.activity
        btStart?.setOnClickListener {
            addActivityViewModel.startActivity(activity)
            bottomSheetDialog.hide()
            showBottomSheet = false
        }
        bottomSheetDialog.setOnDismissListener {
            showBottomSheet = false
        }
        if (showBottomSheet) {
            bottomSheetDialog.show()
        }
    }

}