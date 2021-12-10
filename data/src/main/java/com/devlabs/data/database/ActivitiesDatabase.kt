package com.devlabs.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devlabs.data.database.model.ActivityLocal
import com.devlabs.domain.entity.Activity

@Database(version = 1, entities = [ActivityLocal::class])

abstract class ActivitiesDatabase : RoomDatabase() {
    abstract fun activitiesDao(): ActivitiesDao
}