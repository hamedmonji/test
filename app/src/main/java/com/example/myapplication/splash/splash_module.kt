package com.example.myapplication

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.myapplication.splash.SplashViewModel
import com.example.myapplication.splash.data.LocaleStartupCountDataSource
import com.example.myapplication.splash.data.PreferencesLocaleStartupCountDataSource
import com.example.myapplication.splash.data.StartupCountRepository
import com.example.myapplication.splash.data.StartupCountRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    factory<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
    factory<LocaleStartupCountDataSource> { PreferencesLocaleStartupCountDataSource(get()) }
    factory<StartupCountRepository> { StartupCountRepositoryImpl(get()) }
    viewModel { SplashViewModel(get()) }
}