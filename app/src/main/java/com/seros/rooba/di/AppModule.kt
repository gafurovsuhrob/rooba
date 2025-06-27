package com.seros.rooba.di

import com.seros.rooba.core.data.repository.UserRepositoryImpl
import com.seros.rooba.core.domain.repository.UserRepository
import com.seros.rooba.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Репозитории
    single<UserRepository> { UserRepositoryImpl() }


    // ViewModel-и
    viewModel { HomeViewModel(get()) }
}
