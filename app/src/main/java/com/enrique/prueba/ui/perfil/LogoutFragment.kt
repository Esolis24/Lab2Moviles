package com.enrique.prueba.ui.perfil

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enrique.prueba.MainActivity
import com.enrique.prueba.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_logout.*

class LogoutFragment: Fragment(R.layout.fragment_logout) {
private lateinit var logoutViewModel: LogoutViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        logoutViewModel =
                ViewModelProvider(this).get(LogoutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logout, container, false)
        val button: Button = root.findViewById(R.id.button_logout)
        logoutViewModel.textBot.observe(viewLifecycleOwner,{
            button.text=it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_logout.setOnClickListener{
            val sharedPreferences= this.activity?.
            getSharedPreferences("user_login", Context.MODE_PRIVATE)
            val editor=sharedPreferences?.edit()
            editor?.apply {
                clear()
            }?.apply()
            Toast.makeText(this.context,"Data cleared", Toast.LENGTH_SHORT).show()

            val action= LogoutFragmentDirections.actionNavigationLogoutToNavigationPerfil()
            findNavController().navigate(action)

        }
    }

}