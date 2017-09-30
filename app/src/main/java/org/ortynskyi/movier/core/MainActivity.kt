package org.ortynskyi.movier.core

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import org.ortynskyi.movier.R
import org.ortynskyi.movier.base.BaseActivity
import org.ortynskyi.movier.core.countries.CountryActivity
import org.ortynskyi.movier.core.movies.MovieActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moviesButton = findViewById<Button>(R.id.moviesButton) as Button
        val countriesButton = findViewById<Button>(R.id.countriesButton) as Button

        moviesButton.setOnClickListener { view ->
            val intent = Intent(baseContext, MovieActivity::class.java)
            startActivity(intent)
        }

        countriesButton.setOnClickListener { view ->
            val intent = Intent(baseContext, CountryActivity::class.java)
            startActivity(intent)
        }

    }
}