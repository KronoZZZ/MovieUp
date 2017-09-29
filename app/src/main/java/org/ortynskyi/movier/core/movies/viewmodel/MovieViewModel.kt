package org.ortynskyi.movier.core.movies.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.ortynskyi.movier.core.movies.repository.MovieRepository
import org.ortynskyi.movier.core.movies.viewmodel.model.MovieVm

class MovieViewModel : ViewModel() {

    private val repository: MovieRepository = MovieRepository()
    private val moviesLiveData: MutableLiveData<List<MovieVm>> = MutableLiveData()
    private val cachedMovies: MutableList<MovieVm> = mutableListOf()
    private val disposables: CompositeDisposable = CompositeDisposable()

    private var currentPage: Int = -1
    private var searchQuery: String = ""

    companion object {
        private const val TAG: String = "MovieViewModel"
    }

    fun getMovies(title: String, page: Int): LiveData<List<MovieVm>> {
        if (title != searchQuery) searchMovie(title, page)
        else loadMoviePage(title, page)
        return moviesLiveData
    }

    private fun searchMovie(title: String, page: Int) {
        cachedMovies.clear()
        searchQuery = title
        fetchMovies(title, page)
    }

    private fun loadMoviePage(title: String, page: Int) {
        if (currentPage < page) fetchMovies(title, page)
        else moviesLiveData.value = cachedMovies
    }

    private fun fetchMovies(title: String, page: Int) {
        this.currentPage = page
        disposables.add(repository.searchMovies(title, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext({ cachedMovies.addAll(it) })
                .subscribe({ moviesLiveData.value = it },
                        { Log.d(TAG, "onError: ${it.localizedMessage}") },
                        { Log.d(TAG, "onComplete") }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
