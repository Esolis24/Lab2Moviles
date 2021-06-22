package com.enrique.prueba.ui.perfil

import android.util.Log
import androidx.lifecycle.*
import com.enrique.prueba.modelo.User
import com.enrique.prueba.repositories.users.PerfilRepository

class PerfilViewModel : ViewModel() {
    var userRepository = PerfilRepository()
    var loginStatus=MutableLiveData<Boolean>()
    private var users = MutableLiveData<List<User>>()

    private lateinit var currentlyUser: User

    init {
        Log.i("PerfilViewModel", "PerfilViewModel created!")
        loadUsers()
        users=userRepository.users
    }

    fun loadUsers() {
        userRepository.getAllUsers()
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("PerfilViewModel", "PerfilViewModel destroyed!")
    }

    fun onLogin(mail: String, pass: String){
        for (user in users.value!!) {
            if (user.email == mail && user.pass == pass) {
                currentlyUser=user
                loginStatus.value=true
                return
            }
        }
        loginStatus.value=false
    }
    fun getCurrentUser(): User{
        return currentlyUser
    }
}
