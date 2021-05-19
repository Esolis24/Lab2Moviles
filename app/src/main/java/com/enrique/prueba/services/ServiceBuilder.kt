package com.enrique.prueba.services

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceBuilder {

    private var BASE_URL:String = "http://db1846c773b9.ngrok.io"

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}