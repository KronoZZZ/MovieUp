package org.ortynskyi.movier.core.countries

import android.os.Bundle
import android.support.v7.widget.Toolbar
import org.ortynskyi.movier.R
import org.ortynskyi.movier.base.BaseActivity

class CountryActivity : BaseActivity() {

    private lateinit var toolbar: Toolbar

    private var countryListFragment: CountryListFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        initToolbar()
        if (savedInstanceState == null) {
            countryListFragment = CountryListFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, countryListFragment, CountryListFragment::class.java.name)
                    .commit()
        } else {
            countryListFragment = supportFragmentManager.findFragmentByTag(CountryListFragment::class.java.name) as CountryListFragment?
        }
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}
