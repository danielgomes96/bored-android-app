package com.devlabs.data.database.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devlabs.domain.entity.ProgressStatus
import java.util.*

@Entity(tableName = "activities")
data class ActivityLocal(
    @PrimaryKey
    val key: String,
    @ColumnInfo(name = "progress")
    var progress: ProgressStatus,
    @ColumnInfo(name = "accessibility")
    val accessibility: Float,
    @ColumnInfo(name = "description")
    val activity: String,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "participants")
    val participants: Int,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "start_time")
    val startTime: Long,
    @ColumnInfo(name = "end_time")
    var endTime: Long
)