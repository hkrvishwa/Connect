package org.connect.chat.di

import io.ktor.client.HttpClient
import org.connect.chat.data.LocationRepository
import org.connect.chat.data.LogRepo
import org.connect.chat.data.NoteRepository
import org.connect.chat.data.PermissionRepository
import org.connect.chat.data.UserRepository
import org.connect.chat.domain.usecase.CheckPermissionUseCase
import org.connect.chat.domain.usecase.RequestPermissionUseCase
import org.connect.chat.platform.provideHttpClient
import org.connect.chat.platform.createDatabase
import org.connect.chat.presentation.LocationViewModel
import org.connect.chat.presentation.NoteViewModel
import org.connect.chat.presentation.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {

    single { createDatabase(get()) }

    single { provideHttpClient() }

    single { LogRepo(get()) }
    single { PermissionRepository(get()) }

    factory { CheckPermissionUseCase(get()) }
    factory { RequestPermissionUseCase(get()) }

    single { LocationRepository(get(),get()) }
    viewModel { LocationViewModel(get()) }

    single { NoteRepository(get()) }

    single { NoteViewModel(get()) }

    single { UserRepository(get(),get()) }

    viewModel { UserViewModel(get()) }

}