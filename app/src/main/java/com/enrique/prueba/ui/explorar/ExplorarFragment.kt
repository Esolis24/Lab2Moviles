package com.enrique.prueba.ui.explorar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
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

       /* explorarViewModel.text.observe(viewLifecycleOwner, {
            explorar_search.queryHint = it
        });
        explorarViewModel.ida.observe(viewLifecycleOwner, {
            fecha_salida.hint = it
        });
        explorarViewModel.vuelta.observe(viewLifecycleOwner, {
            fecha_regreso.hint = it
        })*/

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
                    adaptador?.filter(text)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    var text=query
                    text= text!!.toLowerCase()
                    adaptador?.filter(text!!)
                }

                return false
            }
        })

        explorar_search.setOnSearchClickListener{
            if(!explorar_search.query.isNullOrEmpty())
            {var auxList: MutableList<Tours> = ArrayList()
            for(items in adaptador?.getList()!!){
                if(items.nombre_tour==explorar_search.query) {
                    auxList.add(items)
                }
                }
            adaptador?.updateList(auxList)
            }
        }

        fecha_salida.setOnClickListener {
            showDatePickerDialog(fecha_salida)
        }
        fecha_regreso.setOnClickListener {
            showDatePickerDialog(fecha_regreso)
        }
    }

    private fun showDatePickerDialog(fecha: TextView) {
        val newFragment = DatePickerFragment.newInstance { _, year, month, day ->

            val selectedDate = "${day.toString()}-${DateFormatSymbols().months[month - 1].substring(0, 3)}-${year}"
            fecha.text = selectedDate
        }
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }
}



