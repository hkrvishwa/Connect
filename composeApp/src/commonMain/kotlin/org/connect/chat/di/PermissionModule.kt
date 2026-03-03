package org.connect.chat.di

import com.connect.chat.db.AppDatabase
import org.connect.chat.data.LocationRepository
import org.connect.chat.data.LogRepo
import org.connect.chat.data.NoteRepository
import org.connect.chat.data.permission.PermissionRepository
import org.connect.chat.domain.usecase.CheckPermissionUseCase
import org.connect.chat.domain.usecase.RequestPermissionUseCase
import org.connect.chat.platform.createDatabase
import org.connect.chat.presentation.LocationViewModel
import org.connect.chat.presentation.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {

    single { createDatabase(get()) }

    single { LogRepo(get()) }
    single { PermissionRepository(get()) }

    factory { CheckPermissionUseCase(get()) }
    factory { RequestPermissionUseCase(get()) }

    single { LocationRepository(get(),get()) }
    viewModel { LocationViewModel(get()) }

    single { NoteRepository(get()) }

    single { NoteViewModel(get()) }

}