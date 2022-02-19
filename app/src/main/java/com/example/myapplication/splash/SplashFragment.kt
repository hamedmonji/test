package com.example.myapplication.splash

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val startupCount = view.findViewById<TextView>(R.id.startupCount)

        val viewModel : SplashViewModel by viewModel()

        lifecycleScope.launchWhenResumed {
            viewModel.startupCount.collect { count ->
                startupCount.text = getString(R.string.startup_count,count)
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.shouldMoveToJokesScreen.filter { it }.collect {
                findNavController().navigate(R.id.action_splashFragment_to_jokesListFragment)
            }
        }
    }
}