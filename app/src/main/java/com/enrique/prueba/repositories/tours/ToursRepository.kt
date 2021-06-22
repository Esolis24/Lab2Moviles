package com.enrique.prueba.repositories.tours

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.enrique.prueba.Constants.BASE_URL
import com.enrique.prueba.modelo.Tours
import com.enrique.prueba.services.I_ToursService
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

class ToursRepository {
    var tours= MutableLiveData<List<Tours>>()
    init{
        tours.value=arrayListOf()
    }


    private fun getRetrofit(): I_ToursService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(I_ToursService::class.java)
    }

    fun getAllTours(){
        getRetrofit().getAllTours().enqueue(object: Callback<List<Tours>>{
            override fun onFailure(call: Call<List<Tours>>, t: Throwable) {
                Log.d("TAG_","An error happened!")
            }

            override fun onResponse(call: Call<List<Tours>>, response: Response<List<Tours>>) {
                Log.d("TAG_","Repository: ${response.body().toString()}")
                tours.value=response.body().orEmpty()

            }
        })
    }

}

