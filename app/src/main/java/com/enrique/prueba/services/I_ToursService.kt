package com.enrique.prueba.services

import com.enrique.prueba.modelo.Tours
import retrofit2.Call
import retrofit2.http.GET

interface I_ToursService {

    @GET("/api/tours")
    fun getAllTours(): Call<List<Tours>>
}