package org.ortynskyi.movier.core.movies.viewmodel.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.ortynskyi.movier.core.movies.repository.dto.Movie

@SuppressLint("ParcelCreator")
@Parcelize
data class MovieVm(val id: Int,
                   val title: String,
                   val originalTitle: String,
                   val overview: String,
                   val posterPath: String,
                   val voteCount: Int,
                   val voteAverage: Float,
                   val popularity: Float,
                   val adult: Boolean,
                   val video: Boolean,
                   val genres: List<GenreVm>?) : Parcelable {

    constructor(movie: Movie) : this(
            movie.id,
            movie.title,
            movie.originalTitle,
            movie.overview,
            movie.posterPath,
            movie.voteCount,
            movie.voteAverage,
            movie.popularity,
            movie.adult,
            movie.video,
            movie.genres?.map(::GenreVm))

    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}
