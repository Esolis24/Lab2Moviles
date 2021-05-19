package com.enrique.prueba.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R
import kotlinx.android.synthetic.main.fragment_registro.*

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
        return root
    }
}