package org.ortynskyi.movier.core.movies.repository

import io.reactivex.Observable
import org.ortynskyi.movier.core.movies.repository.dto.Movie
import org.ortynskyi.movier.network.MovieApi
import org.ortynskyi.movier.network.RestApi

class MovieWebService {

    private val api: MovieApi = RestApi().movieApi

    fun searchMovies(title: String, page: Int): Observable<List<Movie>> {
        return api.searchMovies(title, page).map { it.results }
    }
}