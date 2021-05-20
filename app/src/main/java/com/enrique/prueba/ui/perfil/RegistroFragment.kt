package com.enrique.prueba.ui.perfil

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R
import com.enrique.prueba.modelo.User
import com.enrique.prueba.ui.dialog.DatePickerFragment
import com.google.android.material.internal.TextWatcherAdapter
import kotlinx.android.synthetic.main.fragment_registro.*
import java.text.DateFormatSymbols

class RegistroFragment:Fragment(R.layout.fragment_registro) {
    private lateinit var registroViewModel: RegistroViewModel
    var nameBoolean:Boolean=false
    var idBoolean:Boolean=false
    var emailBoolean:Boolean=false
    var passBoolean:Boolean=false
    var countryBoolean:Boolean=false
    var birthBoolean:Boolean=false;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registroViewModel =
            ViewModelProvider(this).get(RegistroViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_registro, container, false)
       registroViewModel.registro.observe(viewLifecycleOwner, {
            text_registro.text = it
           button_registro_registro.text=it
        })
        registroViewModel.nombre.observe(viewLifecycleOwner, {
            editText_registro_nombre.hint = it
        })
        registroViewModel.apellidos.observe(viewLifecycleOwner, {
            editText_registro_apellidos.hint = it
        })
        registroViewModel.email.observe(viewLifecycleOwner, {
            editText_registro_email.hint = it
        })
        registroViewModel.password.observe(viewLifecycleOwner, {
            editText_registro_password.hint = it
        })
        registroViewModel.proms.observe(viewLifecycleOwner, {
            TextView_registro_promociones.text = it
        })
        registroViewModel.politica.observe(viewLifecycleOwner, {
            textView_registro_politica_privacidad.text = it
        })
        registroViewModel.politica.observe(viewLifecycleOwner, {
            textView_registro_politica_privacidad.text = it
        })
        registroViewModel.confirmacionPolitica.observe(viewLifecycleOwner,{
            textView_registro_terminos_uso.text=it
        })
        registroViewModel.nacimiento_hint.observe(viewLifecycleOwner,{
            EditText_registro_fecNacimiento.hint=it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EditText_registro_fecNacimiento.setOnClickListener {
            val newFragment = DatePickerFragment.newInstance { _, year, month, day ->
                val selectedDate = "$day-${DateFormatSymbols().months[month - 1].substring(0, 3)}-${year}"
                EditText_registro_fecNacimiento.setText(selectedDate)
            }
            newFragment.show(requireActivity().supportFragmentManager, "datePicker")
        }
        editText_registro_email.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                emailBoolean = (s.toString().trim().isNotEmpty() &&
                        Patterns.EMAIL_ADDRESS.matcher(s.toString().trim()).matches()
                        )
                countryBoolean=countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled=allBooleans()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
            })
        editText_registro_nombre.addTextChangedListener (object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nameBoolean=(s.toString().trim().isNotEmpty())
                countryBoolean=countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled=allBooleans()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        editText_registro_apellidos.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                idBoolean=(s.toString().trim().isNotEmpty())
                countryBoolean=countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled=allBooleans()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        editText_registro_password.addTextChangedListener (object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                passBoolean = (s.toString().trim().isNotEmpty() && s.toString().trim().matches(Regex(
                        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!.%*?&])[A-Za-z\\d@\$.!%*?&]{8,}"
                )))
                countryBoolean=countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled=allBooleans()

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        EditText_registro_fecNacimiento.addTextChangedListener (object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                birthBoolean=(s.toString().trim().isNotEmpty())
                countryBoolean=countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled=allBooleans()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        button_registro_registro.setOnClickListener{
         val name:String=editText_registro_nombre.text.toString()
         val lastname:String=editText_registro_apellidos.text.toString()
            val birth:String=EditText_registro_fecNacimiento.text.toString()
            val pass:String=editText_registro_password.text.toString()
            val country:String=countryPicker.tvCountryInfo.text.toString()
            val email:String=editText_registro_email.text.toString()
        //Armar usuario y enviarlo.
        val user= User(null,lastname,name,email,pass,country,birth)
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
    private fun allBooleans():Boolean{
        return emailBoolean&&passBoolean&&nameBoolean&&idBoolean&&countryBoolean&&birthBoolean
    }
}