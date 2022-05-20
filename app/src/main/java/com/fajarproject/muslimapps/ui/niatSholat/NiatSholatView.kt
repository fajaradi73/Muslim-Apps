package com.fajarproject.muslimapps.ui.niatSholat

import com.fajarproject.muslimapps.models.sholat.ModelNiatSholat

/**
 * Created by Fajar Adi Prasetyo on 20/05/2022.
 */

interface NiatSholatView {
    fun setToolbar()
    fun showDataSuccess(list: MutableList<ModelNiatSholat>)
    fun showDataFailed(message: String)
    fun showLoading()
    fun hideLoading()
}