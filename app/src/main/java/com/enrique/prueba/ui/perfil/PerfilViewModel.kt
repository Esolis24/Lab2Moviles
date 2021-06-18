package com.enrique.prueba.ui.perfil

import android.util.Log
import androidx.lifecycle.*
import com.enrique.prueba.modelo.User
import com.enrique.prueba.repositories.users.PerfilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class PerfilViewModel: ViewModel(){
    var userRepository= PerfilRepository()
    var users=MutableLiveData<List<User>>()
    private val _email= MutableLiveData<String>()
            val email: LiveData<String>
            get() = _email
    private val _pass= MutableLiveData<String>()
        val pass: LiveData<String>
            get()=_pass

    init{
        Log.i("PerfilViewModel","PerfilViewModel created!")
        _email.value=""
        _pass.value=""
        users=userRepository.users
    }
    fun getUsers(){
        userRepository.getAllUsers()
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("PerfilViewModel","PerfilViewModel destroyed!")
    }

        fun onLogin(mail: String, pass: String) :Boolean{
          for(user in users.value!!)
          {
            if(user.email==mail&&user.pass==pass)
                return true
          }
            return false
        }

    }
