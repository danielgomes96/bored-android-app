package com.devlabs.data.di

import com.devlabs.data.repository.ActivityRepositoryImpl
import com.devlabs.domain.repository.ActivityRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        ActivityRepositoryImpl(
            get()
        ) as ActivityRepository
    }
}