package com.fajarproject.muslimapps.models.place

import com.google.gson.annotations.SerializedName

data class ModelGeometry(
    @SerializedName("location")
    var modelLocation: ModelLocation
)
