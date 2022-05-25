package com.fajarproject.muslimapps.ui.alquran

import com.fajarproject.muslimapps.models.alquran.ModelSurah

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

interface AlQuranView {
    fun setToolbar()
    fun setUI()
    fun openSearch()
    fun closeSearch()
    fun showDataSuccess(list: MutableList<ModelSurah>)
    fun showDataFailed(message : String)
    fun showLoading()
    fun hideLoading()
}