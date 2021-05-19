package com.enrique.prueba.ui.perfil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enrique.prueba.R

class LogoutViewModel(application: Application):AndroidViewModel(application) {
    private val _textBotton = MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.logout_botton)
    }
    var textBot: LiveData<String> =_textBotton
}