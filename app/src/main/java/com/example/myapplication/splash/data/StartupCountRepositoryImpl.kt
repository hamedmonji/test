package com.example.myapplication.splash.data

class StartupCountRepositoryImpl(private val localeDataSource: LocaleStartupCountDataSource) :
    StartupCountRepository {
    override suspend fun getStartupCount() = localeDataSource.getStartupCount()
    override suspend fun setStartupCount(count: Int) = localeDataSource.setStartupCount(count)
}