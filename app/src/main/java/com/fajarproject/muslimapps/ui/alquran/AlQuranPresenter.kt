package com.fajarproject.muslimapps.ui.alquran

import android.app.Activity
import com.fajarproject.muslimapps.models.alquran.ModelSurah
import com.fajarproject.muslimapps.networking.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

class AlQuranPresenter(var activity: Activity, var view: AlQuranView) {
    private val api = ApiService.create()
    private val subscriptions = CompositeDisposable()
    fun getQuran() {
        view.showLoading()
        val subscribe = api.getListSurah().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: MutableList<ModelSurah> ->
                view.showDataSuccess(data)
            }, { error ->
                view.showDataFailed(error?.message ?: "")
            }, {
                view.hideLoading()
            })
        subscriptions.add(subscribe)
    }
}