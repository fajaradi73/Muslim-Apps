package com.fajarproject.muslimapps.models.alquran

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import org.parceler.Parcels

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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(arti)
        parcel.writeString(asma)
        parcel.writeString(ayat)
        parcel.writeString(nama)
        parcel.writeString(type)
        parcel.writeString(audio)
        parcel.writeString(nomor)
        parcel.writeString(keterangan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelSurah> {
        override fun createFromParcel(parcel: Parcel): ModelSurah {
            return ModelSurah(parcel)
        }

        override fun newArray(size: Int): Array<ModelSurah?> {
            return arrayOfNulls(size)
        }
    }
}
