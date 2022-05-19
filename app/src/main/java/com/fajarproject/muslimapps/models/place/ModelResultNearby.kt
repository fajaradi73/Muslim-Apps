package com.fajarproject.muslimapps.models.place

import com.google.gson.annotations.SerializedName

/**
 * Created by Fajar Adi Prasetyo on 17/05/2022.
 */

data class ModelResultNearby(
    @SerializedName("results")
    var modelResults : MutableList<ModelResults>
)
