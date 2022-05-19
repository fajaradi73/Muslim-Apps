package com.fajarproject.muslimapps.networking

import com.fajarproject.muslimapps.util.Constant
import com.fajarproject.muslimapps.util.Constant.BASE_URL
import com.fajarproject.muslimapps.util.Constant.BASE_URL_SHOLAT
import com.fajarproject.muslimapps.util.Util
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Create by Fajar Adi Prasetyo on 01/07/2020.
 */

object ApiService {
    fun quran(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(Util.getOkHttp())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    fun maps(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL_MAPS)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(Util.getOkHttp())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    fun sholat(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(Util.getOkHttp())
            .baseUrl(BASE_URL_SHOLAT)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}