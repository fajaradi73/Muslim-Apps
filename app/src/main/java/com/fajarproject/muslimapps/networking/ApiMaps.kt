package com.fajarproject.muslimapps.networking

import com.fajarproject.muslimapps.util.Constant
import com.fajarproject.muslimapps.util.Util
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Create by Fajar Adi Prasetyo on 17/05/2022.
 */

class ApiMaps {

    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_MAPS)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Util.getOkHttp())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}