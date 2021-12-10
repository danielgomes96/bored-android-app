package com.devlabs.data.di

import androidx.room.Room
import com.devlabs.data.database.ActivitiesDatabase
import com.devlabs.data.repository.ActivityRepositoryImpl
import com.devlabs.domain.repository.ActivityRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val ACTIVITIES_DATABASE = "db_activities"

val repositoryModule = module {
    factory {
        ActivityRepositoryImpl(
            get(),
            get()
        ) as ActivityRepository
    }

    single {
        get<ActivitiesDatabase>().activitiesDao()
    }

    single {
        Room.databaseBuilder(androidContext(), ActivitiesDatabase::class.java, ACTIVITIES_DATABASE)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}