package com.example.myapplication.jokesList.data

interface RemoteJokesDataSource {
    suspend fun getJokes() : JokeResponse
}