package com.example.myapplication.jokesList

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokesListFragment : Fragment(R.layout.fragment_jokes_list) {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var loading: ProgressBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading = view.findViewById(R.id.loading)
        val jokesRecyclerView = view.findViewById<RecyclerView>(R.id.jokes)
        val viewModel: JokesViewModel by viewModel()

        refreshLayout = view.findViewById(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener {
            viewModel.loadJokes()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.jokesState.collect { state ->

                    when (state) {
                        is JokesViewModel.JokesState.Success -> {
                            hideAllLoadings()
                            showJokesList(jokesRecyclerView, state)
                        }
                        is JokesViewModel.JokesState.Error -> {
                            hideAllLoadings()
                            Snackbar.make(view, state.reason, Snackbar.LENGTH_LONG).show()
                        }
                        is JokesViewModel.JokesState.Loading -> {
                            loading.isVisible = !refreshLayout.isRefreshing
                        }
                    }
                }
            }
        }

        viewModel.loadJokes()

    }

    private fun hideAllLoadings() {
        loading.isVisible = false
        refreshLayout.isRefreshing = false
    }

    private fun showJokesList(
        jokesRecyclerView: RecyclerView,
        state: JokesViewModel.JokesState.Success
    ) {
        jokesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        jokesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        val jokesAdapter = JokesAdapter(state.jokes) { selectedJoke ->
            findNavController().navigate(
                JokesListFragmentDirections.actionJokesListFragmentToJokeDetailFragment(
                    selectedJoke
                )
            )
        }
        jokesRecyclerView.adapter = jokesAdapter
    }
}