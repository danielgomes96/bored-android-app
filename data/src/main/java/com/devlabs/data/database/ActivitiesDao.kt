package com.devlabs.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devlabs.data.database.model.ActivityLocal
import com.devlabs.domain.entity.Activity

@Dao
interface ActivitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActivity(activity: ActivityLocal)

    @Query("SELECT * FROM activities")
    fun getActivities(): List<ActivityLocal>
}