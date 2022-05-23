package com.fajarproject.muslimapps.ui.doa

import com.fajarproject.muslimapps.models.doa.ModelDoa

/**
 * Created by Fajar Adi Prasetyo on 23/05/2022.
 */

interface DoaView {
    fun setUI()
    fun setToolbar()
    fun showDataSuccess(list: MutableList<ModelDoa>)
    fun showDataFailed(message : String)
    fun showLoading()
    fun hideLoading()
}