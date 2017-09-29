package org.ortynskyi.movier.network.model

import com.google.gson.annotations.SerializedName

open class BaseWrapper(@SerializedName("page") val page: Int,
                       @SerializedName("total_results") val totalResults: Int,
                       @SerializedName("total_pages") val totalPages: Int) {
}