package com.enrique.prueba.ui.explorar

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enrique.prueba.R
import com.enrique.prueba.modelo.Tours
import com.enrique.prueba.ui.dialog.DatePickerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_explorar.*
import java.lang.Character.toLowerCase
import java.text.DateFormatSymbols
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class ExplorarFragment : Fragment() {


    companion object {
        var lista: ArrayList<Tours> = ArrayList()
    }
    private val args: ExplorarFragmentArgs by navArgs()
    private lateinit var explorarViewModel: ExplorarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        explorarViewModel =
                ViewModelProvider(this).get(ExplorarViewModel::class.java);
        val root = inflater.inflate(R.layout.fragment_explorar, container, false);



        return root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        var nav: BottomNavigationView? = this.activity?.findViewById(R.id.nav_view)

        Log.d("testeo", menu.size().toString())
        if (args.username.isNullOrEmpty()&&args.password.isNullOrEmpty())
        {
            Log.d("testeo", "Args vacíos")
        }
        else
        {
            nav?.menu?.removeItem(R.id.navigation_perfil)
            nav?.menu?.add(R.menu.bottom_nav_menu, R.id.navigation_logout, 0, "perfil")
                    ?.setIcon(R.mipmap.ic_persona)

            Log.d("testeo", "Usuario: ${args.username}")
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        if (!args.username.isNullOrEmpty()&&!args.password.isNullOrEmpty())
        {
            this.activity?.invalidateOptionsMenu()
        }

        val recycler_view: RecyclerView = view.findViewById(R.id.recyclerview);
        var adaptador:Adaptador? = null;
        explorarViewModel.loadTours();

        explorarViewModel.tours.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            recycler_view.apply {
               layoutManager = LinearLayoutManager(activity);

               adaptador = Adaptador(it.toMutableList() as ArrayList<Tours>);

               recycler_view.layoutManager = layoutManager;
               recycler_view.adapter = adaptador;

            }

        });



        val layoutManager: RecyclerView.LayoutManager?=
                LinearLayoutManager(this.context,
                        RecyclerView.VERTICAL, false)

        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adaptador

        explorar_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    var text = newText
                    text=text!!.toLowerCase(Locale.ROOT)
                    adaptador?.filter(text,fecha_salida.text.toString().orEmpty(),
                    fecha_regreso.text.toString().orEmpty())
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    var text=query
                    text= text!!.toLowerCase()
                    adaptador?.filter(text!!,fecha_salida.text.toString().orEmpty(),
                    fecha_regreso.text.toString().orEmpty())
                }

                return false
            }
        })


        fecha_salida.setOnClickListener {
            showDatePickerDialog(fecha_salida)
        }

        fecha_regreso.setOnClickListener {
            showDatePickerDialog(fecha_regreso)
        }
        button_clean_ida.setOnClickListener{
            fecha_salida.setText("")
        }
        button_clean_regreso.setOnClickListener{
            fecha_regreso.setText("")
        }
        fecha_salida.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adaptador?.filter(explorar_search.query.toString(),s.toString(),
                    fecha_regreso.text.toString().orEmpty())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        fecha_regreso.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adaptador?.filter(explorar_search.query.toString(),
                    fecha_salida.text.toString().orEmpty(),s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun showDatePickerDialog(fecha: TextView) {
        val newFragment = DatePickerFragment.newInstance { _, year, month, day ->

            val selectedDate = "${day.toString()}-${DateFormatSymbols().months[month].substring(0, 3)}-${year}"
            fecha.text = selectedDate
        }
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }
}



