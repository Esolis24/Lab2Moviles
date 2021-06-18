package com.enrique.prueba.repositories.users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.enrique.prueba.Constants.BASE_URL
import com.enrique.prueba.modelo.User
import com.enrique.prueba.services.I_UserService
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

class PerfilRepository {
var users= MutableLiveData<List<User>>()
init{
    users.value=arrayListOf()
}


    private fun getRetrofit(): I_UserService{
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(I_UserService::class.java)
    }

    fun getAllUsers(){
        getRetrofit().getAllUsers().enqueue(object: Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("TAG_","An error happened!")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("TAG_","Repository: ${response.body().toString()}")
                users.value=response.body().orEmpty()

            }
        })
    }
}