package com.devlabs.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devlabs.domain.entity.Activity

interface ActivitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActivity(activity: Activity)

    @Query("SELECT * FROM activities")
    fun getActivities(): LiveData<List<Activity>>
}