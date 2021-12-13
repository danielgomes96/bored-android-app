package com.devlabs.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devlabs.data.database.model.ActivityLocal
import com.devlabs.domain.entity.ProgressStatus

@Dao
interface ActivitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActivity(activity: ActivityLocal)

    @Query("SELECT * FROM activities")
    fun getActivities(): List<ActivityLocal>

    @Query("UPDATE activities SET progress=:progress, time_spent=:minutes WHERE `key` = :key")
    fun updateActivityProgress(progress: ProgressStatus, key: String, minutes: Int)
}