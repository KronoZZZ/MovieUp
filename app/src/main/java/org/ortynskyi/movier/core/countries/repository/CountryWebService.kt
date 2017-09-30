package org.ortynskyi.movier.core.countries.repository

import io.reactivex.Observable
import org.ortynskyi.movier.core.countries.repository.dto.CountryDto
import org.ortynskyi.movier.network.CountryApi
import org.ortynskyi.movier.network.RestApi

class CountryWebService {

    private val api: CountryApi = RestApi().countryApi

    fun fetchCoutries(): Observable<List<CountryDto>> {
        return api.fetchCountries()
    }
}