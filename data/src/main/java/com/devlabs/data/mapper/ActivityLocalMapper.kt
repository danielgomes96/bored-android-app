package com.devlabs.data.mapper

import com.devlabs.data.database.model.ActivityLocal
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ProgressStatus
import java.util.*

class ActivityLocalMapper : BaseMapper<Activity, ActivityLocal>() {
    override fun transform(entity: Activity): ActivityLocal {
        return ActivityLocal(
            entity.key,
            ProgressStatus.IN_PROGRESS,
            entity.accessibility,
            entity.activity,
            entity.link,
            entity.participants,
            entity.price,
            entity.type,
            System.currentTimeMillis(),
            System.currentTimeMillis()
        )
    }
}