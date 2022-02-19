package com.example.myapplication.jokesList.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokeRepositoryImpl(
    private val remoteJokesDataSource: RemoteJokesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : JokeRepository {
    override suspend fun getJokes(): List<JokeModel> = withContext(dispatcher) {
        val response = remoteJokesDataSource.getJokes()
        if (response.error) {
            throw Throwable("some error that was sent from the backend")
        }
        return@withContext response.jokes
    }
}