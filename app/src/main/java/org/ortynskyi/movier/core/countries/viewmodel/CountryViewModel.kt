package org.ortynskyi.movier.core.countries.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.ortynskyi.movier.core.countries.repository.CountryRepository
import org.ortynskyi.movier.core.countries.viewmodel.model.CountryVm

class CountryViewModel : ViewModel() {

    private val repository: CountryRepository = CountryRepository()
    private val countriesLiveData: MutableLiveData<List<CountryVm>> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()

    companion object {
        private const val TAG: String = "CountryViewModel"
    }

    fun getCountries(): MutableLiveData<List<CountryVm>> {
        fetchCountries()
        return countriesLiveData
    }

    private fun fetchCountries() {
        disposables.add(repository.fetchCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ countriesLiveData.value = it },
                        { Log.d(CountryViewModel.TAG, "onError: ${it.localizedMessage}") },
                        { Log.d(CountryViewModel.TAG, "onComplete") }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}