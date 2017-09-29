package org.ortynskyi.movier.network.model

import com.google.gson.annotations.SerializedName
import org.ortynskyi.movier.core.movies.repository.dto.Movie

class SearchMovieWrapper(@SerializedName("results") val results: List<Movie>) : BaseWrapper(0, 0, 0)