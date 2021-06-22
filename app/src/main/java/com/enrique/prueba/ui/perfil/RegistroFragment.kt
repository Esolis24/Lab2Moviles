package com.enrique.prueba.ui.perfil

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R
import com.enrique.prueba.ui.dialog.DatePickerFragment
import kotlinx.android.synthetic.main.fragment_registro.*

class RegistroFragment : Fragment(R.layout.fragment_registro) {
    private lateinit var model: RegistroViewModel
    var nameBoolean: Boolean = false
    var idBoolean: Boolean = false
    var emailBoolean: Boolean = false
    var passBoolean: Boolean = false
    var countryBoolean: Boolean = false
    var birthBoolean: Boolean = false;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          model =
              ViewModelProvider(this).get(RegistroViewModel::class.java)
        model.signupStatus.observe(viewLifecycleOwner, Observer {
            if(it)
            {
                Toast.makeText(this.context, "Usuario Creado", Toast.LENGTH_SHORT).show()
                this.activity?.onBackPressed()
            }
            else{
                var emergentWin: AlertDialog.Builder= AlertDialog.Builder(this.context)
                emergentWin.setTitle("Error:")
                emergentWin.setMessage("Registro fallido. Por favor, intente nuevamente.")
                emergentWin.setPositiveButton("Aceptar", null)
                var ventanita=emergentWin.create()
                ventanita.show()

            }
        })

        val root = inflater.inflate(R.layout.fragment_registro, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EditText_registro_fecNacimiento.setOnClickListener {
            val newFragment = DatePickerFragment.newInstance { _, year, month, day ->
                var selectedDate = "$day-$month-${year}"
                if (selectedDate[1] == '-')//1-2-2020
                    selectedDate = "0$selectedDate"
                if (selectedDate[4] == '-')//11-3-2021
                    selectedDate = selectedDate.substring(0, 3) + "0${selectedDate.substring(3)}"
                EditText_registro_fecNacimiento.setText(selectedDate)
            }
            newFragment.show(requireActivity().supportFragmentManager, "datePicker")
        }
        editText_registro_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                emailBoolean = (s.toString().trim().isNotEmpty() &&
                        Patterns.EMAIL_ADDRESS.matcher(s.toString().trim()).matches()
                        )
                countryBoolean = countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled = allBooleans()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        editText_registro_nombre.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nameBoolean = (s.toString().trim().isNotEmpty())
                countryBoolean = countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled = allBooleans()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        editText_registro_apellidos.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                idBoolean = (s.toString().trim().isNotEmpty())
                countryBoolean = countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled = allBooleans()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        editText_registro_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                passBoolean = (s.toString().trim().isNotEmpty() && s.toString().trim().matches(
                    Regex(
                        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!.%*?&])[A-Za-z\\d@\$.!%*?&]{8,}"
                    )
                ))
                countryBoolean = countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled = allBooleans()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        EditText_registro_fecNacimiento.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                birthBoolean = (s.toString().trim().isNotEmpty())
                countryBoolean = countryPicker.tvCountryInfo.text.toString().trim().isNotEmpty()
                button_registro_registro.isEnabled = allBooleans()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        button_registro_registro.setOnClickListener {
            val name: String = editText_registro_nombre.text.toString()
            val identificacion: String = editText_registro_apellidos.text.toString()
            val birth: String = EditText_registro_fecNacimiento.text.toString()
            val pass: String = editText_registro_password.text.toString()
            val country: String = countryPicker.tvCountryInfo.text.toString()
            val email: String = editText_registro_email.text.toString()
            model.onSignUp(identificacion, name, email, email, pass, country, birth)
        }
    }

    private fun allBooleans(): Boolean {
        return emailBoolean && passBoolean && nameBoolean && idBoolean && countryBoolean && birthBoolean
    }
}