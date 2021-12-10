package com.devlabs.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ProgressStatus

class ActivitiesAdapter(
    private val activitiesList: List<Activity>,
    private val clickItem: (Activity) -> Unit
) : RecyclerView.Adapter<ActivitiesAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate((R.layout.item_activity), parent, false))
    }

    override fun onBindViewHolder(holder: ActivitiesAdapter.Holder, position: Int) {
        holder.bind(activitiesList[position], clickItem)
    }

    override fun getItemCount() = activitiesList.size

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.activity_title)
        private val tvProgress: TextView = view.findViewById(R.id.activity_progress)
        private val tvDescription: TextView = view.findViewById(R.id.activity_description)

        fun bind(activity: Activity, clickItem: (Activity) -> Unit) = with (activity) {
            tvTitle.text = activity.activity
            when (progressStatus) {
                ProgressStatus.IN_PROGRESS -> {
                    tvProgress.text = "In Progress"
                }
                ProgressStatus.ABANDONED -> {
                    tvProgress.text = "Abandoned"
                }
                ProgressStatus.FINISHED -> {
                    tvProgress.text = "Finished"
                }
            }

            itemView.setOnClickListener {
                clickItem(activity)
            }
        }
    }
}