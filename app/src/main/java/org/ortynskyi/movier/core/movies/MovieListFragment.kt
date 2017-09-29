package org.ortynskyi.movier.core.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.ortynskyi.movier.R
import org.ortynskyi.movier.base.BaseRetainFragment
import org.ortynskyi.movier.core.movies.adapter.MovieAdapter
import org.ortynskyi.movier.core.movies.adapter.OnMovieClick
import org.ortynskyi.movier.core.movies.viewmodel.MovieViewModel
import org.ortynskyi.movier.core.movies.viewmodel.model.MovieVm
import org.ortynskyi.movier.view.EndlessScrollListener

class MovieListFragment : BaseRetainFragment(), OnMovieClick {

    companion object {
        const val DEFAULT_PAGE = 1
        const val MIN_SEARCH_LENGTH = 3
        const val INITIAL_SEARCH_FRAZE: String = "Mad"
        const val TRANSITION_POSTER: String = "poster_transition"
        const val SAVE_STATE_CURRENT_PAGE: String = "SAVE_STATE_CURRENT_PAGE"
        const val SAVE_STATE_SEARCH_FRAZE: String = "SAVE_STATE_SEARCH_FRAZE"
    }

    private lateinit var movieRecycler: RecyclerView

    private lateinit var moviesViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter
    private var scrollListener: EndlessScrollListener? = null
    private var searchFraze: String = INITIAL_SEARCH_FRAZE
    private var currentPage: Int = DEFAULT_PAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            exitTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)
            sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_movies, container, false)
        moviesViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieRecycler = rootView.findViewById(R.id.moviesRecycler)
        baseActivity.setToolbarTitle(getString(R.string.app_name))
        initAdapter()
        return rootView
    }

    override fun onNewStateInstance() {
        listenMovies()
    }

    override fun onStateInstanceRestored(savedInstanceState: Bundle) {
        currentPage = savedInstanceState.getInt(SAVE_STATE_CURRENT_PAGE)
        searchFraze = savedInstanceState.getString(SAVE_STATE_SEARCH_FRAZE)
        scrollListener?.setPage(currentPage)
        moviesViewModel.getMovies(searchFraze, currentPage)
        listenMovies()
    }

    override fun saveFragmentState(outInstanceState: Bundle?) {
        outInstanceState?.putInt(SAVE_STATE_CURRENT_PAGE, currentPage)
        outInstanceState?.putString(SAVE_STATE_SEARCH_FRAZE, searchFraze)
    }

    override fun onMovieClick(position: Int, sharedView: View) {
        val transitionName: String = TRANSITION_POSTER + position
        val movie: MovieVm = adapter.movies[position]
        val fragment: MovieDetailFragment = MovieDetailFragment.newInstance(movie, transitionName)
        baseActivity.replaceFragmentWithTransition(fragment, transitionName, sharedView)
    }

    fun onSearchTyped(query: String) {
        if (!TextUtils.isEmpty(query) && query.length >= MIN_SEARCH_LENGTH) {
            searchFraze = query
            adapter.movies.clear()
            scrollListener?.resetPage()
            moviesViewModel.getMovies(query, DEFAULT_PAGE)
        }
    }

    private fun listenMovies() {
        moviesViewModel.getMovies(searchFraze, currentPage).observe(this, Observer {
            adapter.addMovies(it!!)
        })
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(context)
        adapter = MovieAdapter(arrayListOf(), this)
        movieRecycler.layoutManager = layoutManager
        movieRecycler.adapter = adapter
        scrollListener = object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                this@MovieListFragment.currentPage = currentPage
                moviesViewModel.getMovies(searchFraze, currentPage)
            }
        }
        movieRecycler.addOnScrollListener(scrollListener)
    }
}