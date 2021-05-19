package com.enrique.prueba.services

import android.util.Log
import com.enrique.prueba.modelo.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestAPIService {

    fun addUser(user:User, result: (User?)->Unit){
        val retrofit = ServiceBuilder.buildService(I_UserService::class.java)

        retrofit.registrarUsuario(user).enqueue(
                object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        val usuario:User? = response.body()
                        result(usuario)
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.d("Retro_","Fallo registrar usuario: ${t.message}")
                        result(null)
                    }
                })
    }

    fun logIn(credenciales: MutableMap<String,String>, result:(Boolean?)->Unit){
        val retrofit = ServiceBuilder.buildService(I_UserService::class.java)

        retrofit.login(credenciales).enqueue(
                object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        val respuesta: Boolean? = response.body()
                        result(respuesta)
                    }

                    override fun onFailure(call: Call<Boolean>, t: Throwable) {
                        Log.d("Retro_","Fallo registrar usuario: ${t.message}")
                        result(null)
                    }
                })

    }


}