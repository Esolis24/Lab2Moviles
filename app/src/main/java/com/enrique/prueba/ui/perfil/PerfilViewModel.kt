package com.enrique.prueba.ui.perfil

import android.app.Application
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enrique.prueba.R

class PerfilViewModel(application: Application) : AndroidViewModel(application) {

    private val _textTit = MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.text_perfil_titulo)
    }
    private val _hintEmail = MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.email_hint)
    }
    var textTit: LiveData<String> = _textTit
    var hintEm: LiveData<String> = _hintEmail

}