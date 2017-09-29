package org.ortynskyi.movier.core.movies.repository.dto

import com.google.gson.annotations.SerializedName

class Movie(@SerializedName("id") val id: Int,
            @SerializedName("imdb_id") val imdbId: String,
            @SerializedName("title") val title: String,
            @SerializedName("original_title") val originalTitle: String,
            @SerializedName("original_language") val language: String,
            @SerializedName("overview") val overview: String,
            @SerializedName("release_date") val releaseDate: String,
            @SerializedName("poster_path") val posterPath: String,
            @SerializedName("backdrop_path") val backdropPath: String,
            @SerializedName("homepage") val homepage: String,
            @SerializedName("tagline") val tagLine: String,
            @SerializedName("status") val status: String,
            @SerializedName("budget") val budget: Int,
            @SerializedName("runtime") val runtime: Int,
            @SerializedName("revenue") val revenue: Int,
            @SerializedName("vote_count") val voteCount: Int,
            @SerializedName("vote_average") val voteAverage: Float,
            @SerializedName("popularity") val popularity: Float,
            @SerializedName("adult") val adult: Boolean,
            @SerializedName("video") val video: Boolean,
            @SerializedName("genres") val genres: List<Genre>?,
            @SerializedName("production_companies") val productCompanies: List<ProductionCompany>,
            @SerializedName("production_countries") val productCountries: List<ProductionCountry>,
            @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>)