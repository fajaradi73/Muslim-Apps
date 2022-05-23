package com.fajarproject.muslimapps.models.doa

import com.google.gson.annotations.SerializedName

data class ModelDoa(

    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("arabic")
    val arabic: String? = null,

    @field:SerializedName("latin")
    val latin: String? = null,

    @field:SerializedName("translation")
    val translation: String? = null

)