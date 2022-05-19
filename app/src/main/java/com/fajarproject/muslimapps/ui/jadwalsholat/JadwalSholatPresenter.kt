package com.fajarproject.muslimapps.ui.jadwalsholat

import com.fajarproject.muslimapps.models.sholat.DaftarKota
import com.fajarproject.muslimapps.models.sholat.ModelSholat
import com.fajarproject.muslimapps.networking.ApiService
import com.fajarproject.muslimapps.util.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by Fajar Adi Prasetyo on 19/05/2022.
 */

class JadwalSholatPresenter(var view: JadwalSholatView) {
    private val api = ApiService.sholat()
    private val subscriptions = CompositeDisposable()

    fun getKota() {
        view.showLoading()
        val subscribe = api.getListKota().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: MutableList<DaftarKota> ->
                view.showDataKotaSuccess(data)
            }, { error ->
                view.hideLoading()
                view.showDataFailed(error?.message ?: "")
                error.printStackTrace()
            }, {
                view.hideLoading()
            })
        subscriptions.add(subscribe)
    }

    fun getJadwalSholat(id: String) {
        view.showLoading()
        val tanggal = Util.convertLongIntoDate(Date().time, "dd", false) ?: ""
        val tahun = Util.convertLongIntoDate(Date().time, "yyyy", false) ?: ""
        val bulan = Util.convertLongIntoDate(Date().time, "MM", false) ?: ""
        val subscribe = api.getJadwalSholat(id, tahun, bulan, tanggal).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: ModelSholat ->
                view.showDataSholatSuccess(data)
            }, { error ->
                view.hideLoading()
                view.showDataFailed(error?.message ?: "")
                error.printStackTrace()
            }, {
                view.hideLoading()
            })
        subscriptions.add(subscribe)
    }

}