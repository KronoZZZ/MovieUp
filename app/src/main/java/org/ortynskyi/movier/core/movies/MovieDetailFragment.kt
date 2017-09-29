package org.ortynskyi.movier.core.movies

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.ortynskyi.movier.R
import org.ortynskyi.movier.base.BaseFragment
import org.ortynskyi.movier.core.movies.viewmodel.model.MovieVm

class MovieDetailFragment : BaseFragment() {

    private lateinit var movieImage: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieDescription: TextView

    companion object {
        val ARG_MOVIE: String = "ARG_MOVIE"
        val ARG_TRANSITION_NAME: String = "ARG_TRANSITION_NAME"

        fun newInstance(movie: MovieVm, transitionName: String): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_MOVIE, movie)
            args.putString(ARG_TRANSITION_NAME, transitionName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            enterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.frament_movie_detail, container, false)
        baseActivity.setBackButtonVisibility(true)
        movieImage = view.findViewById(R.id.movieImage)
        movieTitle = view.findViewById(R.id.movieTitle)
        movieDescription = view.findViewById(R.id.movieDescription)
        setupMovie()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseActivity.setBackButtonVisibility(false)
    }

    private fun setupMovie() {
        val transitionName: String = arguments.getString(ARG_TRANSITION_NAME)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            movieImage.transitionName = transitionName
        }
        val movie: MovieVm = arguments.getParcelable(ARG_MOVIE)
        baseActivity.setToolbarTitle(movie.title)
        movieTitle.text = movie.title
        movieDescription.text = movie.overview
        Picasso.with(context).load(movie.getPosterUrl()).into(movieImage)
    }
}