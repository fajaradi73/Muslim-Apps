package com.fajarproject.muslimapps.networking

import com.fajarproject.muslimapps.models.sholat.DaftarKota
import com.fajarproject.muslimapps.models.alquran.ModelAyat
import retrofit2.http.GET
import com.fajarproject.muslimapps.models.alquran.ModelSurah
import com.fajarproject.muslimapps.models.place.ModelResultNearby
import com.fajarproject.muslimapps.models.sholat.ModelKota
import com.fajarproject.muslimapps.models.sholat.ModelSholat
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Create by Fajar Adi Prasetyo on 17/05/2022.
 */

interface ApiInterface {
    @GET("/99c279bb173a6e28359c/data")
    fun getListSurah(): Observable<MutableList<ModelSurah>>

    @GET("/99c279bb173a6e28359c/surat/{nomor}")
    fun getDetailSurah(
        @Path("nomor") nomor: String
    ): Observable<MutableList<ModelAyat>>

    @GET("place/nearbysearch/json")
    fun getDataResult(
        @Query("key") key: String,
        @Query("keyword") keyword: String,
        @Query("location") location: String,
        @Query("rankby") rankby: String
    ): Observable<ModelResultNearby>

    @GET("sholat/kota/semua")
    fun getListKota(): Observable<MutableList<DaftarKota>>

    @GET("sholat/jadwal/{idkota}/{tahun}/{bulan}/{tanggal}")
    fun getJadwalSholat(
        @Path("idkota") idKota: String,
        @Path("tahun") tahun : String,
        @Path("bulan") bulan : String,
        @Path("tanggal") tanggal : String
    ): Observable<ModelSholat>

}