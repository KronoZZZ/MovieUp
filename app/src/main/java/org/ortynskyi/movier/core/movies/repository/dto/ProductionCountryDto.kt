package org.ortynskyi.movier.core.movies.repository.dto

import com.google.gson.annotations.SerializedName

class ProductionCountryDto(@SerializedName("name") val name: String,
                           @SerializedName("iso_3166_1") val abbreviation: String)