package com.enrique.prueba.ui.perfil

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
        val username: TextView = root.findViewById(R.id.user_name)
        val sharedPreferences = this.activity?.getSharedPreferences("user_login", Context.MODE_PRIVATE)
        username.text = sharedPreferences?.getString("NAME_KEY",null)

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

            var nav: BottomNavigationView? = this.activity?.findViewById(R.id.nav_view)


                nav?.menu?.removeItem(R.id.navigation_logout)
                nav?.menu?.add(R.menu.bottom_nav_menu,R.id.navigation_perfil,0,"perfil")
                    ?.setIcon(R.mipmap.ic_persona)
            val action= LogoutFragmentDirections.actionNavigationLogoutToNavigationPerfil()
            findNavController().navigate(action)

        }
    }

}