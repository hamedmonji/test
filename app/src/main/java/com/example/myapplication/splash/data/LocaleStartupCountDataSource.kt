package com.example.myapplication.splash.data

interface LocaleStartupCountDataSource {
    suspend fun getStartupCount(): Int
    suspend fun setStartupCount(count: Int)
}