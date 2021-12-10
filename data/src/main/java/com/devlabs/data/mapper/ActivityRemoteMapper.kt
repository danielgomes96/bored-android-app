package com.devlabs.data.mapper

import com.devlabs.data.dto.DTOActivity
import com.devlabs.domain.entity.Activity
import com.devlabs.domain.entity.ProgressStatus

class ActivityRemoteMapper : BaseMapper<DTOActivity, Activity>() {
    override fun transform(entity: DTOActivity): Activity {
        return Activity(
            entity.activity,
            entity.type,
            entity.participants,
            entity.price,
            entity.link,
            entity.key,
            entity.accessibility,
            ProgressStatus.IN_PROGRESS
        )
    }
}