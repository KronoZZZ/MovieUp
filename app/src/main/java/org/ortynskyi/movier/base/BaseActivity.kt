package org.ortynskyi.movier.base

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import org.ortynskyi.movier.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    fun replaceFragmentWithTransition(fragment: Fragment, transitionName: String, view: View) {
        if (!isFinishing) {
            val fragmentName: String = fragment.javaClass.simpleName
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, fragmentName)
            transaction.addToBackStack(fragmentName)
            transaction.addSharedElement(view, transitionName)
            transaction.commit()
        } else {
            Log.e("BaseActivity", "Finishing")
        }
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fragmentName: String = fragment.javaClass.simpleName
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, fragmentName)
        if (addToBackStack) {
            transaction.addToBackStack(fragmentName)
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        transaction.commit()
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun setBackButtonVisibility(visibility: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(visibility)
    }
}