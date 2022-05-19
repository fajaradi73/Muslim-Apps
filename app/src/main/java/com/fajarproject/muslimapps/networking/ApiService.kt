package com.fajarproject.muslimapps.networking

import com.fajarproject.muslimapps.util.Constant.BASE_URL
import com.fajarproject.muslimapps.util.Util
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Create by Fajar Adi Prasetyo on 01/07/2020.
 */

class ApiService {

    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Util.getOkHttp())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}