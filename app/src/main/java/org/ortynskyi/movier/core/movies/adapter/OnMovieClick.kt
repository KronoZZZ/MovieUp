package org.ortynskyi.movier.core.movies.adapter

import android.view.View

interface OnMovieClick {

    fun onMovieClick(position: Int, sharedView: View)
}