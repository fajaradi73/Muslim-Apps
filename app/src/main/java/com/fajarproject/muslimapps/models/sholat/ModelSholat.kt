package com.fajarproject.muslimapps.models.sholat

import com.google.gson.annotations.SerializedName

data class ModelSholat(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)