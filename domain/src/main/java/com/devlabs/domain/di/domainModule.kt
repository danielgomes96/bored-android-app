package com.devlabs.domain.di

import com.devlabs.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetActivityUseCaseImpl(
            get()
        ) as GetActivityUseCase
    }
    factory {
        StartActivityUseCaseImpl(
            get()
        ) as StartActivityUseCase
    }
    factory {
        GetStartedActivitiesUseCaseImpl(
            get()
        ) as GetStartedActivitiesUseCase
    }
    factory {
        FinishActivityUseCaseImpl(
            get()
        ) as FinishActivityUseCase
    }
    factory {
        AbandonActivityUseCaseImpl(
            get()
        ) as AbandonActivityUseCase
    }
}