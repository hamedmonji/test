package com.example.myapplication.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.splash.data.StartupCountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val startupCountRepository: StartupCountRepository) : ViewModel() {

    private val _startupCount = MutableStateFlow(0)
    val startupCount : StateFlow<Int> = _startupCount
    private val _shouldMoveToJokesScreen = MutableStateFlow(false)
    val shouldMoveToJokesScreen : StateFlow<Boolean> = _shouldMoveToJokesScreen

    init {
        viewModelScope.launch {
            _startupCount.value = startupCountRepository.getStartupCount()
        }
        startStartupTimeout()
    }

    private fun startStartupTimeout() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(SPLASH_TIMEOUT_IN_MILLIS)
            increaseStartupCount()
            _shouldMoveToJokesScreen.value = true
        }
    }

    private suspend fun increaseStartupCount() {
        startupCountRepository.setStartupCount(_startupCount.value + 1)
    }


    companion object {
        const val SPLASH_TIMEOUT_IN_MILLIS = 3000L
    }

}