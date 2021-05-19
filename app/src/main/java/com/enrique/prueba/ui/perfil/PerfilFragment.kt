package com.enrique.prueba.ui.perfil

import android.app.AlertDialog
import android.content.Context
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enrique.prueba.R
import com.enrique.prueba.services.RestAPIService
import kotlinx.android.synthetic.main.fragment_logout.*
import kotlinx.android.synthetic.main.fragment_perfil.*


class PerfilFragment : Fragment(R.layout.fragment_perfil) {


    private lateinit var perfilViewModel: PerfilViewModel
    var emailBoolean: Boolean = false
    var passBoolean: Boolean = false
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        perfilViewModel =
            ViewModelProvider(this).get(PerfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)
        val textViewTitulo: TextView = root.findViewById(R.id.textView_pefil_titulo)
        val editTextEmail: EditText =root.findViewById(R.id.editText_perfil_email)
        val editTextPassword: EditText=root.findViewById(R.id.editText_perfil_password)
        val textViewForgetPassword: TextView=root.
        findViewById(R.id.textView_perfil_password_olvidada)
        val buttonLogin: Button =root.findViewById(R.id.button_perfil_login)
        val textViewNewUser: TextView=root.findViewById(R.id.textView_perfil_no_tiene_cuenta)
        val buttonSignUp: Button=root.findViewById(R.id.button_perfil_registro)
        perfilViewModel.textTit.observe(viewLifecycleOwner, {
            textViewTitulo.text = it
        })
        perfilViewModel.hintEm.observe(viewLifecycleOwner, {
            editTextEmail.hint = it
        })
        perfilViewModel.hintPass.observe(viewLifecycleOwner, {
            editTextPassword.hint = it
        })
        perfilViewModel.textForgetPass.observe(viewLifecycleOwner, {
            textViewForgetPassword.text = it
        })
        perfilViewModel.textLogin.observe(viewLifecycleOwner, {
            buttonLogin.text = it
        })
        perfilViewModel.textNewUser.observe(viewLifecycleOwner, {
            textViewNewUser.text = it
        })
        perfilViewModel.textSignUp.observe(viewLifecycleOwner, {
            buttonSignUp.text = it
        })
        return root
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
                        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!.%*?&])[A-Za-z\\d@\$.!%*?&]{8,15}"
                )))
                button_perfil_login.isEnabled = emailBoolean && passBoolean

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
                //CONFIGURACION BOTON PERFIL//
        button_perfil_registro.setOnClickListener{
            Log.d("Testeo", "Boton registro seleccionado")
            val action=PerfilFragmentDirections.actionNavigationPerfilToNavigationRegistro()
            findNavController().navigate(action)
        }

        button_perfil_login.setOnClickListener{
            val apiService = RestAPIService();
            val email = editText_perfil_email.text.toString()
            val pass = editText_perfil_password.text.toString()

            val credenciales = mutableMapOf<String,String>()
            credenciales.put("email", email)
            credenciales.put("pass", pass)

            apiService.logIn(credenciales){
                if(it!=null){
                    val email=editText_perfil_email.text.toString()
                    val pass=editText_perfil_password.text.toString()
                    saveData(it.username,email, pass)
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
    }
    private fun saveData(username:String, email: String, pass: String){
        val sharedPreferences=this.activity?.
        getSharedPreferences("user_login", Context.MODE_PRIVATE)
        val editor=sharedPreferences?.edit()
        editor?.apply {
            putString("EMAIL_KEY", email)
            putString("PASS_KEY", pass)
            putString("NAME_KEY",username)
        }?.apply()
        Toast.makeText(this.context, "Data saved", Toast.LENGTH_SHORT).show()
    }
}