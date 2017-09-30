package org.ortynskyi.movier.network

import io.reactivex.Observable
import org.ortynskyi.movier.core.countries.repository.dto.CountryDto
import retrofit2.http.GET

interface CountryApi {

    @GET("all")
    fun fetchCountries(): Observable<List<CountryDto>>
}