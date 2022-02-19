package com.example.myapplication.splash.data

interface StartupCountRepository {
    suspend fun getStartupCount(): Int
    suspend fun setStartupCount(count: Int)
}