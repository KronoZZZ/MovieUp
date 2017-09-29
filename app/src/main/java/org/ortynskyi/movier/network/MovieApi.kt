package org.ortynskyi.movier.network

import io.reactivex.Observable
import org.ortynskyi.movier.network.model.SearchMovieWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/search/movie")
    fun searchMovies(@Query("query") query: String, @Query("page") page: Int): Observable<SearchMovieWrapper>
}