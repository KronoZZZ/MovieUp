package org.ortynskyi.movier.core.movies.adapter

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.ortynskyi.movier.R
import org.ortynskyi.movier.core.movies.MovieListFragment
import org.ortynskyi.movier.core.movies.viewmodel.model.MovieVm

class MovieAdapter(val movies: ArrayList<MovieVm>, private val listener: OnMovieClick)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieTitle.text = movie.title
        holder.movieDescription.text = movie.overview
        Picasso.with(holder.itemView.context).load(movie.getPosterUrl()).into(holder.movieImage)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.movieImage.transitionName = MovieListFragment.TRANSITION_POSTER + position
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movies: List<MovieVm>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val listener: OnMovieClick) : RecyclerView.ViewHolder(itemView) {

        val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        val movieDescription: TextView = itemView.findViewById(R.id.movieDescription)
        val movieImage: ImageView = itemView.findViewById(R.id.movieImage)

        init {
            itemView.setOnClickListener { listener.onMovieClick(adapterPosition, it.findViewById(R.id.movieImage)) }
        }
    }
}