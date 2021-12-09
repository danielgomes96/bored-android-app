package com.devlabs.domain.di

import com.devlabs.domain.usecase.GetActivityUseCase
import com.devlabs.domain.usecase.GetActivityUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetActivityUseCaseImpl(
            get()
        ) as GetActivityUseCase
    }
}