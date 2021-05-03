package com.enrique.prueba.ui.perfil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enrique.prueba.R

class PerfilViewModel(application: Application) : AndroidViewModel(application) {

    private val _textTit = MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.text_perfil_titulo)
    }
    private val _hintEmail = MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.perfil_email_hint)
    }
    private val _hintPassword = MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.perfil_password_hint)
    }
    private val _textForgetPassword=MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.perfil_password_olvidada)
    }
    private val _textLoginButton=MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.perfil_login_button)
    }
    private val _textNewUser=MutableLiveData<String>().apply {
        this.value = getApplication<Application>().getString(R.string.perfil_sin_cuenta)
    }
    private val _textSignUpButton=MutableLiveData<String>().apply {
        this.value = getApplication<Application>().getString(R.string.perfil_registro_button)
    }
    var textTit: LiveData<String> = _textTit
    var hintEm: LiveData<String> = _hintEmail
    var hintPass: LiveData<String> = _hintPassword
    var textForgetPass: LiveData<String> =_textForgetPassword
    var textLogin: LiveData<String> = _textLoginButton
    var textNewUser: LiveData<String> =_textNewUser
    var textSignUp: LiveData<String> =_textSignUpButton
}