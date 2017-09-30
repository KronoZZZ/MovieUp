package org.ortynskyi.movier.core.movies.repository

import io.reactivex.Observable
import org.ortynskyi.movier.core.movies.repository.dto.MovieDto
import org.ortynskyi.movier.core.movies.viewmodel.model.MovieVm

class MovieRepository {

    private val movieService: MovieWebService = MovieWebService()

    fun searchMovies(title: String, page: Int): Observable<List<MovieVm>> {
        return movieService.searchMovies(title, page)
                .map { t: List<MovieDto> -> t.map(::MovieVm) }
    }
}