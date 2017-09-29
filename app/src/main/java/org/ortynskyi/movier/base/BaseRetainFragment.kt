package org.ortynskyi.movier.base

import android.os.Bundle
import android.view.View

abstract class BaseRetainFragment : BaseFragment() {

    private var fragmentLocalState: Bundle? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readFragmentState(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentLocalState = fragmentLocalState ?: Bundle()
        saveFragmentState(fragmentLocalState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveFragmentState(outState)
    }

    private fun readFragmentState(savedInstanceState: Bundle?) {
        fragmentLocalState = savedInstanceState ?: fragmentLocalState
        if (fragmentLocalState == null) {
            onNewStateInstance()
        } else {
            onStateInstanceRestored(fragmentLocalState as Bundle)
        }
    }

    abstract fun onNewStateInstance()

    abstract fun onStateInstanceRestored(savedInstanceState: Bundle)

    abstract fun saveFragmentState(outInstanceState: Bundle?)
}