package com.devlabs.activities.di

import com.devlabs.activities.AddActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AddActivityViewModel(get(), get())
    }
}