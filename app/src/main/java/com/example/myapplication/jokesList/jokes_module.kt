package com.example.myapplication.jokesList

import com.example.myapplication.jokesList.data.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val jokesModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    factory<JokeService> {
        val retrofit : Retrofit by inject()
        retrofit.create(JokeService::class.java)
    }
    factory<RemoteJokesDataSource> {RemoteJokesDataSourceImpl(get())}
    factory<JokeRepository> { JokeRepositoryImpl(get()) }
    viewModel { JokesViewModel(get()) }
}