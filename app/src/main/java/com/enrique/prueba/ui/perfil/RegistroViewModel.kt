package com.enrique.prueba.ui.perfil

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enrique.prueba.R
import com.enrique.prueba.modelo.User
import com.enrique.prueba.repositories.users.PerfilRepository
import com.google.gson.annotations.SerializedName

class RegistroViewModel : ViewModel() {
    var userRepository = PerfilRepository()
    var signupStatus = MutableLiveData<Boolean>()

    init {
        Log.i("RegistroViewModel", "RegistroViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RegistroViewModel", "RegistroViewModel destroyed!")
    }

    fun onSignUp(
        user_id: String,
        name: String,
        username: String,
        email: String,
        pass: String,
        pais: String,
        nacimiento: String
    ){
    var u1= User(null,user_id,name,username,email,pass,pais,nacimiento)
        userRepository.onSignUp(u1)
        while(userRepository.signUpStatus.value==false);
        signupStatus.value= true

    }
}