package com.fajarproject.muslimapps.ui.masjid

import com.fajarproject.muslimapps.models.place.ModelResultNearby

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

interface MasjidView {
    fun setUI()
    fun showDataSuccess(data : ModelResultNearby)
    fun showDataFailed(message : String)
    fun showLoading()
    fun hideLoading()
}