package org.ortynskyi.movier.network.model

import com.google.gson.annotations.SerializedName
import org.ortynskyi.movier.core.movies.repository.dto.MovieDto

class SearchMovieWrapper(@SerializedName("results") val results: List<MovieDto>) : BaseWrapper(0, 0, 0)