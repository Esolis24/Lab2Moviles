package com.enrique.prueba.ui.explorar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R

class ExplorarFragment : Fragment() {

    private lateinit var explorarViewModel: ExplorarViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        explorarViewModel =
                ViewModelProvider(this).get(ExplorarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explorar, container, false)
        val textView: TextView = root.findViewById(R.id.text_explorar)
        explorarViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}