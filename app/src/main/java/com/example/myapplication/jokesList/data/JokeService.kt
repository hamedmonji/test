package com.example.myapplication.jokesList.data

import retrofit2.http.GET

interface JokeService {
    @GET("/joke/Any?amount=20")
    suspend fun getJokes(): JokeResponse
}

data class JokeResponse(val jokes: List<JokeModel>, val error: Boolean)