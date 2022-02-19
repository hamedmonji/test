package com.example.myapplication.jokesList.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteJokesDataSourceImpl(
    private val jokesService: JokeService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteJokesDataSource {
    override suspend fun getJokes(): JokeResponse = withContext(dispatcher) {
        return@withContext jokesService.getJokes()
    }
}