package com.enrique.prueba.ui.explorar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ExplorarViewModel (val titulo:String,
                              val imagen:String,
                              val precio:Double,
                              val estrellas:Double,
                              val CantidadOpiniones:Int) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is explorar Fragment"
    }
    val text: LiveData<String> = _text
}