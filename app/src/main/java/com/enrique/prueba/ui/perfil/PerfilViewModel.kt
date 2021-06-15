package com.enrique.prueba.ui.perfil

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enrique.prueba.R

class PerfilViewModel: ViewModel(){
    init{
        Log.i("PerfilViewModel","PerfilViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("PerfilViewModel","PerfilViewModel destroyed!")
    }

}