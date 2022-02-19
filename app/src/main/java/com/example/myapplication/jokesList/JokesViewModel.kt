package com.example.myapplication.jokesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.jokesList.data.JokeModel
import com.example.myapplication.jokesList.data.JokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JokesViewModel(private val jokesRepository: JokeRepository) : ViewModel() {
    private val _jokesState = MutableStateFlow<JokesState>(JokesState.Loading)
    val jokesState: StateFlow<JokesState> = _jokesState

    fun loadJokes() {
        viewModelScope.launch {
            _jokesState.value = JokesState.Loading
            try {
                val jokes = jokesRepository.getJokes()
                _jokesState.value = JokesState.Success(jokes)
            } catch (e: Throwable) {
                _jokesState.value = JokesState.Error(e.message!!)
            }
        }
    }

    sealed class JokesState {
        object Loading : JokesState()
        class Success(val jokes: List<JokeModel>) : JokesState()
        class Error(val reason: String) : JokesState()
    }

}