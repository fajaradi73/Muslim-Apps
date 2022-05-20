package com.fajarproject.muslimapps.ui.ayatKursi

/**
 * Created by Fajar Adi Prasetyo on 20/05/2022.
 */

interface AyatKursiView {
    fun setToolbar()
    fun setAction()
    fun showDataSuccess(data : HashMap<String, String>)
    fun showDataFailed(message : String)
    fun showLoading()
    fun hideLoading()
}