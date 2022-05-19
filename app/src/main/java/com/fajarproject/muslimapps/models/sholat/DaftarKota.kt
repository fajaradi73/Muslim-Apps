package com.fajarproject.muslimapps.models.sholat

import com.google.gson.annotations.SerializedName

/**
 * Created by Fajar Adi Prasetyo on 17/05/2022.
 */

data class DaftarKota(
    @SerializedName("id")
    var id : String? ,
    @SerializedName("lokasi")
    var name : String?
)
