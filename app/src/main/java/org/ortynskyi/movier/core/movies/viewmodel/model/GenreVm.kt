package org.ortynskyi.movier.core.movies.viewmodel.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.ortynskyi.movier.core.movies.repository.dto.GenreDto

@SuppressLint("ParcelCreator")
@Parcelize
data class GenreVm(val id: Int, val name: String) : Parcelable {

    constructor(genreDto: GenreDto) : this(genreDto.id, genreDto.name)
}