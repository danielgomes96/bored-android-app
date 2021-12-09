package com.devlabs.activities.fragment

import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.devlabs.activities.R
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ResultWrapper
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class AddActivityFragment : Fragment() {

    private lateinit var btGetActivities: Button
    private lateinit var spinnerTypes: Spinner
    private lateinit var progressBar: ProgressBar
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
        spinnerTypes = view.findViewById(R.id.spinner_types)
        progressBar = view.findViewById(R.id.progress_bar)
        btGetActivities.setOnClickListener {
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
        CoroutineScope(Dispatchers.Main).launch {
            addActivityViewModel.activityLiveData.collect { result ->
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
        val btStart = bottomSheetDialog.findViewById<TextView>(R.id.activity_btn_start)
        tvTitle?.text = activity.activity
        btStart?.setOnClickListener {
            addActivityViewModel.startActivity()
        }
        bottomSheetDialog.show()
    }
}