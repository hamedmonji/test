package com.example.myapplication.jokesList.data

interface JokeRepository {
    suspend fun getJokes() : List<JokeModel>
}