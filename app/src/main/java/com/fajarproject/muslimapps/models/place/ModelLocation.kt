package com.fajarproject.muslimapps.models.place

import com.google.gson.annotations.SerializedName

data class ModelLocation(
    @SerializedName("lat")
    var lat : Double = 0.0,

    @SerializedName("lng")
    var lng : Double = 0.0
)
