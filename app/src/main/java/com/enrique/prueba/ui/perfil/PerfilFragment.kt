package com.enrique.prueba.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R

class PerfilFragment : Fragment() {

    private lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        perfilViewModel =
            ViewModelProvider(this).get(PerfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)
        val textViewTitulo: TextView = root.findViewById(R.id.textView_pefil_titulo)
        val editTextEmail: EditText =root.findViewById(R.id.editText_perfil_email)
        perfilViewModel.textTit.observe(viewLifecycleOwner,  {
            textViewTitulo.text = it
          })
        perfilViewModel.hintEm.observe(viewLifecycleOwner,{
            editTextEmail.hint=it
        })

        return root
    }
}