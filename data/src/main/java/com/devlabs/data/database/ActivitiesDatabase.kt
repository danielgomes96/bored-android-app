package com.devlabs.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devlabs.domain.entity.Activity

@Database(version = 1, entities = [Activity::class])

abstract class ActivitiesDatabase : RoomDatabase() {
    abstract fun travelDao(): ActivitiesDao
}