package com.enrique.prueba.services

import com.enrique.prueba.modelo.User
import retrofit2.Call
import retrofit2.http.*

interface I_UserService {

    @GET("/api/users")
    fun getAllUsers(): Call<List<User>>

    @Headers("Content-Type: application/json")
    @POST("/api/users/registrar")
    fun registrarUsuario(@Body usuario:User):Call<User>

    @POST("/api/users/login")
    fun login(@Body crecenciales: MutableMap<String,String>):Call<Boolean>
}