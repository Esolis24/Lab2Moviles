package com.enrique.prueba.ui.notificaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R
import com.enrique.prueba.ui.favoritos.FavoritosViewModel

class NotificacionesFragment: Fragment()  {
    private lateinit var notificacionesViewModel: NotificacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificacionesViewModel =
            ViewModelProvider(this).get(NotificacionesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notificaciones, container, false)
        val textView: TextView = root.findViewById(R.id.text_notificaciones)
        notificacionesViewModel.text.observe(viewLifecycleOwner,  {
            textView.text = it
        })
        return root
        }
}