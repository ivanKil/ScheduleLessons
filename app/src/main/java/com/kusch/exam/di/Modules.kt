package com.kusch.exam.di

import com.kusch.exam.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val applicationModule = module {

    viewModel { HomeViewModel() }
}
