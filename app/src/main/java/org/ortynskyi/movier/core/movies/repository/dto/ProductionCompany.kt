package org.ortynskyi.movier.core.movies.repository.dto

import com.google.gson.annotations.SerializedName

data class ProductionCompany(@SerializedName("id") val id: Int,
                             @SerializedName("name") val name: String)