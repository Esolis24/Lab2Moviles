package com.enrique.prueba.ui.explorar

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enrique.prueba.R

class ExplorarViewModel : ViewModel() {

    init{
        Log.i("ExplorarViewModel","ExplorarViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ExplorarViewModel","ExplorarViewModel destroyed!")
    }

}