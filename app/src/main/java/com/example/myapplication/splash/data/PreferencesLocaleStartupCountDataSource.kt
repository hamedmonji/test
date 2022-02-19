package com.example.myapplication.splash.data

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PreferencesLocaleStartupCountDataSource(
    private val preferences: SharedPreferences,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocaleStartupCountDataSource {
    override suspend fun getStartupCount() = withContext(dispatcher) {
        preferences.getInt(STARTUP_COUNT, 0)
    }

    override suspend fun setStartupCount(count: Int) : Unit = withContext(dispatcher) {
        preferences.edit().putInt(STARTUP_COUNT,count).commit()
    }

    companion object {
        const val STARTUP_COUNT = "startup_count"
    }
}