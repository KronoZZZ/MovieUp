package org.ortynskyi.movier.core.countries

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_countries.*
import org.ortynskyi.movier.R
import org.ortynskyi.movier.base.BaseFragment
import org.ortynskyi.movier.core.countries.adapter.CountryAdapter
import org.ortynskyi.movier.core.countries.viewmodel.CountryViewModel

class CountryListFragment : BaseFragment() {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_countries, container, false)
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        baseActivity.setToolbarTitle(getString(R.string.app_name))
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        countryViewModel.getCountries().observe(this, Observer {
            adapter.addCountries(it!!)
        })
    }

    private fun initAdapter() {
        adapter = CountryAdapter(arrayListOf())
        countryRecycler.layoutManager = LinearLayoutManager(context)
        countryRecycler.adapter = adapter
    }
}