package com.example.myapplication.jokesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.jokesList.data.JokeModel

class JokesAdapter(private val jokes: List<JokeModel>, private val onClick: (JokeModel) -> Unit) :
    RecyclerView.Adapter<JokesAdapter.JokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_joke, parent, false)
        return JokeViewHolder(view) { position -> onClick(jokes[position]) }
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokes[position]
        holder.bind(joke)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    class JokeViewHolder(itemView: View, private val onClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val category = itemView.findViewById<TextView>(R.id.category)

        init {
            itemView.setOnClickListener { onClick(adapterPosition) }
        }

        fun bind(joke: JokeModel) {
            title.text = joke.title
            category.text = joke.category
        }
    }
}