package com.fajarproject.muslimapps.models.alquran

import com.google.gson.annotations.SerializedName

/**
 * Created by Fajar Adi Prasetyo on 17/05/2022.
 */

data class ModelAyat(
    @SerializedName("ar")
    var arab : String?,

    @SerializedName("id")
    var indo : String?,

    @SerializedName("nomor")
    var nomor : String?
)
