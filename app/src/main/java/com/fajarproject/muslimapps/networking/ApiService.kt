package com.fajarproject.muslimapps.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Create by Fajar Adi Prasetyo on 01/07/2020.
 */

class ApiService {

    companion object {
        private const val BASE_URL = "https://api.npoint.io/"
        fun getQuran(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}