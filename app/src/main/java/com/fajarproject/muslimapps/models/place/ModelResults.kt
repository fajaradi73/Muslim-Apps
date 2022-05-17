package com.fajarproject.muslimapps.models.place

import com.google.gson.annotations.SerializedName

/**
 * Created by Fajar Adi Prasetyo on 17/05/2022.
 */

data class ModelResults(
    @SerializedName("geometry")
    var modelGeometry: ModelGeometry,

    @SerializedName("name")
    var name: String?,

    @SerializedName("vicinity")
    var vicinity: String?,

    @SerializedName("place_id")
    var placeId: String?,

    @SerializedName("rating")
    var rating: Double = 0.0
)
