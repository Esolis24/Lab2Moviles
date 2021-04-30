package com.enrique.prueba.ui.notificaciones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificacionesViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notificaciones Fragment"
    }
    val text: LiveData<String> = _text
}