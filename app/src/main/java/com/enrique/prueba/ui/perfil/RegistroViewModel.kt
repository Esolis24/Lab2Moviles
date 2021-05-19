package com.enrique.prueba.ui.perfil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enrique.prueba.R

class RegistroViewModel(application: Application) : AndroidViewModel(application) {
    private val _registro = MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.registro)
    }
    private val _nombre = MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.registro_nombres_hint)
    }
    private val _apellidos = MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.registro_apellidos_hint)
    }
    private val _email = MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.email_hint)
    }
    private val _password = MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.password_hint)
    }
    private val _proms = MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.registro_promociones)
    }
    private val _politica = MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.registro_politica_privacidad)
    }
    private val _confirmacionPolitica = MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.registro_confirmacion)
    }
    private val _nacimiento_hint =MutableLiveData<String>().apply {
        value = getApplication<Application>().getString(R.string.birth_hint)
    }
    val registro: LiveData<String> = _registro
    val nombre: LiveData<String> =_nombre
    val apellidos: LiveData<String> =_apellidos
    val email: LiveData<String> =_email
    val password: LiveData<String> =_password
    val proms: LiveData<String> =_proms
    val politica: LiveData<String> =_politica
    val confirmacionPolitica: LiveData<String> =_confirmacionPolitica
    val nacimiento_hint: LiveData<String> =_nacimiento_hint
}