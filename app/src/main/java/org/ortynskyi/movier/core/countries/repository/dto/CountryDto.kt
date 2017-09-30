package org.ortynskyi.movier.core.countries.repository.dto

import com.google.gson.annotations.SerializedName

class CountryDto(
        @SerializedName("name") val name: String,
        @SerializedName("alpha2Code") val alpha2Code: String,
        @SerializedName("alpha3Code") val alpha3Code: String,
        @SerializedName("capital") val capital: String,
        @SerializedName("region") val region: String,
        @SerializedName("subregion") val subRegion: String,
        @SerializedName("population") val population: Int,
        @SerializedName("area") val area: Float,
        @SerializedName("borders") val borders: List<String>,
        @SerializedName("latlng") val latLng: List<Float>,
        @SerializedName("currencies") val currencies: List<String>,
        @SerializedName("languages") val languages: List<String>,
        @SerializedName("timezones") val timezones: List<String>
)