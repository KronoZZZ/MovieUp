package org.ortynskyi.movier.core.movies.repository.dto

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(@SerializedName("name") val name: Int,
                          @SerializedName("iso_639_1") val abbreviation: String)