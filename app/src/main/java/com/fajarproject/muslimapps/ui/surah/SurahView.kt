package com.fajarproject.muslimapps.ui.surah

import com.fajarproject.muslimapps.models.alquran.ModelAyat

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

interface SurahView {
    fun setToolbar()
    fun setUI()
    fun setAction()
    fun showDataSuccess(data : MutableList<ModelAyat>)
    fun showDataFailed(message : String)
    fun showLoading()
    fun hideLoading()
}