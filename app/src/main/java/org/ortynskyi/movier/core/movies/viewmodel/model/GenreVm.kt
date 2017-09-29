package org.ortynskyi.movier.core.movies.viewmodel.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.ortynskyi.movier.core.movies.repository.dto.Genre

@SuppressLint("ParcelCreator")
@Parcelize
data class GenreVm(val id: Int, val name: String) : Parcelable {

    constructor(genre: Genre) : this(genre.id, genre.name)
}