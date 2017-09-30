package org.ortynskyi.movier.core.movies.viewmodel.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.ortynskyi.movier.core.movies.repository.dto.MovieDto

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

    constructor(movieDto: MovieDto) : this(
            movieDto.id,
            movieDto.title,
            movieDto.originalTitle,
            movieDto.overview,
            movieDto.posterPath,
            movieDto.voteCount,
            movieDto.voteAverage,
            movieDto.popularity,
            movieDto.adult,
            movieDto.video,
            movieDto.genreDtos?.map(::GenreVm))

    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}
