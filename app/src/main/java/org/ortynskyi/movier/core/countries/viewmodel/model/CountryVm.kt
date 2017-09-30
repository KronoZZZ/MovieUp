package org.ortynskyi.movier.core.countries.viewmodel.model

import org.ortynskyi.movier.core.countries.repository.dto.CountryDto

class CountryVm(val name: String,
                val alpha2Code: String,
                val alpha3Code: String,
                val capital: String,
                val region: String,
                val subRegion: String,
                val population: Int,
                val area: Float,
                val borders: List<String>,
                val latLng: List<Float>,
                val currencies: List<String>,
                val languages: List<String>,
                val timezones: List<String>) {

    constructor(country: CountryDto) : this(
            country.name,
            country.alpha2Code,
            country.alpha3Code,
            country.capital,
            country.region,
            country.subRegion,
            country.population,
            country.area,
            country.borders,
            country.latLng,
            country.currencies,
            country.languages,
            country.timezones)
}