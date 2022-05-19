package com.fajarproject.muslimapps.models.sholat

import com.google.gson.annotations.SerializedName

data class Koordinat(

	@field:SerializedName("lintang")
	val lintang: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("bujur")
	val bujur: String? = null
)