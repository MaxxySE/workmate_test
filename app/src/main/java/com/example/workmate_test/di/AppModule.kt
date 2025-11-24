package com.example.workmate_test.di

import com.example.workmate_test.data.repository.UserRepositoryImpl
import com.example.workmate_test.domain.repository.UserRepository
import com.example.workmate_test.domain.usecase.GenerateUserUseCase
import com.example.workmate_test.domain.usecase.GetUserByIdUseCase
import com.example.workmate_test.domain.usecase.GetUserListUseCase
import com.example.workmate_test.presentation.user.details.DetailsViewModel
import com.example.workmate_test.presentation.user.generate.GenerateViewModel
import com.example.workmate_test.presentation.user.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    factory { GenerateUserUseCase(get()) }
    factory { GetUserListUseCase(get()) }
    factory { GetUserByIdUseCase(get()) }

    viewModel { GenerateViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}