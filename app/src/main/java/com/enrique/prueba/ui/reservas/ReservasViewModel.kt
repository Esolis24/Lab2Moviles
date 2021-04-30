package com.enrique.prueba.ui.reservas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReservasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reservas Fragment"
    }
    val text: LiveData<String> = _text
}