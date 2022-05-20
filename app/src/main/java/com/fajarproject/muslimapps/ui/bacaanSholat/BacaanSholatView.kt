package com.fajarproject.muslimapps.ui.bacaanSholat

import com.fajarproject.muslimapps.models.sholat.ModelNiatSholat

/**
 * Created by Fajar Adi Prasetyo on 20/05/2022.
 */

interface BacaanSholatView {
    fun setToolbar()
    fun showDataSuccess(list: MutableList<ModelNiatSholat>)
    fun showDataFailed(message: String)
    fun showLoading()
    fun hideLoading()
}