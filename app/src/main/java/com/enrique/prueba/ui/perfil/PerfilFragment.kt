package com.enrique.prueba.ui.perfil

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enrique.prueba.R
import com.enrique.prueba.services.RestAPIService
import kotlinx.android.synthetic.main.fragment_logout.*
import kotlinx.android.synthetic.main.fragment_perfil.*


class PerfilFragment : Fragment(R.layout.fragment_perfil) {


    private val model: PerfilViewModel by viewModels()
    private var sharedPreferences: SharedPreferences?=null
    var emailBoolean: Boolean = false
    var passBoolean: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=this.activity?.
        getSharedPreferences("user_login", Context.MODE_PRIVATE)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
      //  model =
        //    ViewModelProvider(this).get(PerfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)

        return root
    }

    override fun onPause() {
        super.onPause()
        this.view?.findViewById<EditText>(R.id.editText_perfil_email)?.setText("")
        this.view?.findViewById<EditText>(R.id.editText_perfil_password)?.setText("")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       //VALIDACION DE EMAIL Y CONTRASENA
        editText_perfil_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                emailBoolean = (s.toString().trim().isNotEmpty() &&
                        Patterns.EMAIL_ADDRESS.matcher(s.toString().trim()).matches()
                        )
                button_perfil_login.isEnabled = emailBoolean && passBoolean
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        editText_perfil_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                passBoolean = (s.toString().trim().isNotEmpty() && s.toString().trim().matches(Regex(
                        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!.%*?&])[A-Za-z\\d@\$.!%*?&]{8,}"
                )))
                button_perfil_login.isEnabled = emailBoolean && passBoolean

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
                //CONFIGURACION BOTON PERFIL//
        model.getUsers()
        model.users.observe(viewLifecycleOwner, Observer {
            Log.d("TAG_",it.toString())

        })
        button_perfil_registro.setOnClickListener{
            Log.d("Testeo", "Boton registro seleccionado")
            val action=PerfilFragmentDirections.actionNavigationPerfilToNavigationRegistro()
            findNavController().navigate(action)
        }
        //CONFIGURACION BOTON LOGIN//
        button_perfil_login.setOnClickListener{
            val email = editText_perfil_email.text.toString()
            val pass = editText_perfil_password.text.toString()

                if(model.onLogin(email,pass)){
                    saveData(email, pass)
                    val action= PerfilFragmentDirections.actionNavigationPerfilToNavigationExplorar(
                        email,
                        pass
                    )
                    findNavController().navigate(action)
                }else{
                    var emergentWin: AlertDialog.Builder=AlertDialog.Builder(this.context)
                    emergentWin.setTitle("Error:")
                    emergentWin.setMessage("El usuario no existe")
                    emergentWin.setPositiveButton("Aceptar", null)
                    var ventanita=emergentWin.create()
                    ventanita.show()
                }

        }
    }
    private fun saveData( email: String, pass: String){
        val editor=sharedPreferences?.edit()
        editor?.apply {
            putString("EMAIL_KEY", email)
            putString("PASS_KEY", pass)
        }?.apply()
        Toast.makeText(this.context, "Data saved", Toast.LENGTH_SHORT).show()
    }
}