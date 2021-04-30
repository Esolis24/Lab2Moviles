package com.enrique.prueba.ui.explorar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExplorarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is explorar Fragment"
    }
    val text: LiveData<String> = _text
}