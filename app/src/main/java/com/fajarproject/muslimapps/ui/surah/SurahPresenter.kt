package com.fajarproject.muslimapps.ui.surah

import android.app.Activity
import com.fajarproject.muslimapps.models.alquran.ModelAyat
import com.fajarproject.muslimapps.networking.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

class SurahPresenter(var activity: Activity, var view: SurahView) {
    private val api = ApiService.create()
    private val subscriptions = CompositeDisposable()
    fun getSurah(nomor: String) {
        view.showLoading()
        val subscribe = api.getDetailSurah(nomor).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: MutableList<ModelAyat> ->
                view.showDataSuccess(data)
            }, { error ->
                view.showDataFailed(error?.message ?: "")
            }, {
                view.hideLoading()
            })
        subscriptions.add(subscribe)
    }
}