package com.enrique.prueba.ui.perfil

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enrique.prueba.R

class LogoutViewModel:ViewModel() {
    init{
        Log.i("LogoutViewModel","PerfilViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LogoutViewModel","PerfilViewModel destroyed!")
    }
}