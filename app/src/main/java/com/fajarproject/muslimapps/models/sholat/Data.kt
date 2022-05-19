package com.fajarproject.muslimapps.models.sholat

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("jadwal")
	val jadwal: Jadwal? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("daerah")
	val daerah: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("koordinat")
	val koordinat: Koordinat? = null
)