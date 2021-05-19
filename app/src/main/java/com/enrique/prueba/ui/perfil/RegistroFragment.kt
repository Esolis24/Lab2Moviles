package com.enrique.prueba.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R
import com.enrique.prueba.ui.dialog.DatePickerFragment
import kotlinx.android.synthetic.main.fragment_registro.*
import java.text.DateFormatSymbols

class RegistroFragment:Fragment(R.layout.fragment_registro) {
    private lateinit var registroViewModel: RegistroViewModel

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
            editText_registro_email.hint = it
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

        button_registro_registro.setOnClickListener{
         val name:String=editText_registro_nombre.text.toString()
         val lastname:String=editText_registro_apellidos.text.toString()
            val birth:String=EditText_registro_fecNacimiento.text.toString()
            val pass:String=editText_registro_password.text.toString()
            val country:String=countryPicker.tvCountryInfo.text.toString()
            val email:String=editText_registro_email.text.toString()
        //Armar usuario y enviarlo.
        }
    }
}