package org.connect.chat.di

import org.connect.chat.data.LocationRepository
import org.connect.chat.data.permission.PermissionRepository
import org.connect.chat.platform.PermissionManager
import org.connect.chat.domain.usecase.CheckPermissionUseCase
import org.connect.chat.domain.usecase.RequestPermissionUseCase
import org.connect.chat.presentation.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val permissionModule = module {

    single { PermissionRepository(get()) }

    factory { CheckPermissionUseCase(get()) }
    factory { RequestPermissionUseCase(get()) }

    single { LocationRepository(get()) }
    viewModel { LocationViewModel(get()) }
}