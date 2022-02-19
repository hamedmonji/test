package com.example.myapplication.jokesList.jokeDetailDialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class JokeDetailFragment : BottomSheetDialogFragment() {
    private val args: JokeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            val completeJoke = findViewById<TextView>(R.id.completeJoke)
            val category = findViewById<TextView>(R.id.category)
            val language = findViewById<TextView>(R.id.language)
            val id = findViewById<TextView>(R.id.id)

            val joke = args.joke
            completeJoke.text = joke.completeJoke
            category.text = joke.category
            language.text = joke.language
            id.text = joke.id.toString()
        }

    }
}