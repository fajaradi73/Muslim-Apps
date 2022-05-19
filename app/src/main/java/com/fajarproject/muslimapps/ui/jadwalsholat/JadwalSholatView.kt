package com.fajarproject.muslimapps.ui.jadwalsholat

import com.fajarproject.muslimapps.models.sholat.DaftarKota
import com.fajarproject.muslimapps.models.sholat.ModelSholat

/**
 * Created by Fajar Adi Prasetyo on 19/05/2022.
 */

interface JadwalSholatView {
    fun setToolbar()
    fun setUI()
    fun showLoading()
    fun hideLoading()
    fun showDataKotaSuccess(list: MutableList<DaftarKota>)
    fun showDataSholatSuccess(data : ModelSholat)
    fun showDataFailed(message : String)
}