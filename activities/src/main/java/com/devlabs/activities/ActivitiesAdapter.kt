package com.devlabs.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ProgressStatus
import java.util.*

class ActivitiesAdapter(
    private var activitiesList: List<Activity>,
    private val clickItem: (Activity) -> Unit,
    private val context: Context,
) : RecyclerView.Adapter<ActivitiesAdapter.Holder>() {

    fun setupData(activityList: List<Activity>) {
        this.activitiesList = activityList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate((R.layout.item_activity), parent, false))
    }

    override fun onBindViewHolder(holder: ActivitiesAdapter.Holder, position: Int) {
        holder.bind(context, activitiesList[position], clickItem)
    }

    override fun getItemCount() = activitiesList.size

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.activity_title)
        private val tvProgress: TextView = view.findViewById(R.id.activity_progress)
        private val tvStartTime: TextView = view.findViewById(R.id.activity_start_time)
        private val tvEndTime: TextView = view.findViewById(R.id.activity_end_time)
        private val tvSpent: TextView = view.findViewById(R.id.activity_time_spent)

        fun bind(context: Context, activity: Activity, clickItem: (Activity) -> Unit) = with (activity) {
            tvTitle.text = activity.activity
            when (progressStatus) {
                ProgressStatus.IN_PROGRESS -> {
                    tvProgress.text = context.getString(R.string.in_progress_status)
                }
                ProgressStatus.ABANDONED -> {
                    tvProgress.text = context.getString(R.string.abandoned_status)
                }
                ProgressStatus.FINISHED -> {
                    tvProgress.text = context.getString(R.string.finished_status)
                }
            }

            tvStartTime.text = activity.startDate.toString()
            tvEndTime.text = activity.endDate.toString()
            tvSpent.text = String.format(context.getString(R.string.time_spent_text), activity.timeSpent.toString())

            itemView.setOnClickListener {
                if (activity.progressStatus == ProgressStatus.IN_PROGRESS) {
                    clickItem(activity)
                }
            }
        }
    }
}