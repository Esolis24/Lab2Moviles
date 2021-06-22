package com.enrique.prueba.ui.perfil

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enrique.prueba.R

class RegistroViewModel :ViewModel() {
    init{
        Log.i("RegistroViewModel","RegistroViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RegistroViewModel","RegistroViewModel destroyed!")
    }

}