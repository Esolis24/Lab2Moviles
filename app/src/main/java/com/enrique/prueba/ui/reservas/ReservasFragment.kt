package com.enrique.prueba.ui.reservas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R

class ReservasFragment : Fragment() {

    private lateinit var reservasViewModel: ReservasViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        reservasViewModel =
                ViewModelProvider(this).get(ReservasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_reservas, container, false)
        val textView: TextView = root.findViewById(R.id.text_reservas)
        reservasViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}