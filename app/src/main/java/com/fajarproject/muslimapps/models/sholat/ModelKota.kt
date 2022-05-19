package com.fajarproject.muslimapps.models.sholat

import com.google.gson.annotations.SerializedName

/**
 * Created by Fajar Adi Prasetyo on 19/05/2022.
 */

data class ModelKota(
    @SerializedName("kota")
    var listKota: MutableList<DaftarKota>
)
