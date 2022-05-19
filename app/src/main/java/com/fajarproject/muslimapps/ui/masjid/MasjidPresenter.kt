package com.fajarproject.muslimapps.ui.masjid

import android.app.Activity
import com.fajarproject.muslimapps.BuildConfig
import com.fajarproject.muslimapps.models.place.ModelResultNearby
import com.fajarproject.muslimapps.networking.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

class MasjidPresenter(var activity: Activity, var view: MasjidView) {
    private val api = ApiService.maps()
    private val subscriptions = CompositeDisposable()
    fun getMasjid(location: String) {
        view.showLoading()
        val apiKey = BuildConfig.MAPS_API_KEY
        val subscribe =
            api.getDataResult(apiKey, "Masjid", location, "distance").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data: ModelResultNearby ->
                    view.showDataSuccess(data)
                }, { error ->
                    view.hideLoading()
                    view.showDataFailed(error?.message ?: "")
                }, {
                    view.hideLoading()
                })
        subscriptions.add(subscribe)
    }
}