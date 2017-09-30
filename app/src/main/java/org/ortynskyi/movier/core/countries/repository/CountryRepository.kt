package org.ortynskyi.movier.core.countries.repository

import io.reactivex.Observable
import org.ortynskyi.movier.core.countries.repository.dto.CountryDto
import org.ortynskyi.movier.core.countries.viewmodel.model.CountryVm

class CountryRepository {

    private val webService: CountryWebService = CountryWebService()

    fun fetchCountries(): Observable<List<CountryVm>> {
        return webService.fetchCoutries()
                .map { t: List<CountryDto> -> t.map(::CountryVm) }
    }
}