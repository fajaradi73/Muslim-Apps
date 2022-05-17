package com.fajarproject.muslimapps.models.alquran

import com.google.gson.annotations.SerializedName

/**
 * Created by Fajar Adi Prasetyo on 17/05/2022.
 */

data class ModelSurah(
    @SerializedName("arti")
    var arti: String?,

    @SerializedName("asma")
    var asma: String?,

    @SerializedName("ayat")
    var ayat: String?,

    @SerializedName("nama")
    var nama: String?,

    @SerializedName("type")
    var type: String?,

    @SerializedName("audio")
    var audio: String?,

    @SerializedName("nomor")
    var nomor: String?,

    @SerializedName("keterangan")
    var keterangan: String?
)
