package com.example.myapplication

import android.app.Application
import com.example.myapplication.jokesList.jokesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    init {
        startKoin {
            androidContext(this@App)
            modules(splashModule, jokesModule)
        }
    }
}