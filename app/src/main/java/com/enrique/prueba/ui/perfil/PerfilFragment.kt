package com.enrique.prueba.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R
import org.w3c.dom.Text

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
        val editTextPassword: EditText=root.findViewById(R.id.editText_perfil_password)
        val textViewForgetPassword: TextView=root.
        findViewById(R.id.textView_perfil_password_olvidada)
        val buttonLogin: Button =root.findViewById(R.id.button_perfil_login)
        val textViewNewUser: TextView=root.findViewById(R.id.textView_perfil_no_tiene_cuenta)
        val buttonSignUp: Button=root.findViewById(R.id.button_perfil_registro)
        perfilViewModel.textTit.observe(viewLifecycleOwner,  {
            textViewTitulo.text = it
          })
        perfilViewModel.hintEm.observe(viewLifecycleOwner,{
            editTextEmail.hint=it
        })
        perfilViewModel.hintPass.observe(viewLifecycleOwner,{
            editTextPassword.hint=it
        })
        perfilViewModel.textForgetPass.observe(viewLifecycleOwner,{
            textViewForgetPassword.text=it
        })
        perfilViewModel.textLogin.observe(viewLifecycleOwner,{
            buttonLogin.text=it
        })
        perfilViewModel.textNewUser.observe(viewLifecycleOwner,{
            textViewNewUser.text=it
        })
        perfilViewModel.textSignUp.observe(viewLifecycleOwner,{
            buttonSignUp.text=it
        })
        return root
    }
}