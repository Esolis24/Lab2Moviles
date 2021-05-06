package com.enrique.prueba.ui.explorar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enrique.prueba.R
import com.enrique.prueba.modelo.Tours

class ExplorarFragment : Fragment() {

    companion object {
        var lista:ArrayList<Tours> = ArrayList()
    }

    private lateinit var explorarViewModel: ExplorarViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        explorarViewModel =
                ViewModelProvider(this).get(ExplorarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explorar, container, false)

        //val textView: TextView = root.findViewById(R.id.text_explorar)
//        explorarViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })
        return root
    }
//    override fun onCreateView(inflater:LayoutInflater,
//                              container: ViewGroup?,
//                              savedInstanceState: Bundle?):View?{
//        return inflater.inflate(R.layout.fragment_explorar,container,false)
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        super.onViewCreated(view, savedInstanceState)
        var recycler_view: RecyclerView = view.findViewById(R.id.recyclerview)
        var adaptador:Adaptador = Adaptador(lista)

        var layoutManager: RecyclerView.LayoutManager?=
                LinearLayoutManager(this.context,
                        RecyclerView.VERTICAL, false)

        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adaptador

        if(adaptador.getItemCount()<=0){
            var auxList:MutableList<Tours> = ArrayList()
            auxList.add(Tours("Inglaterra",25.0,"tour1",3,2.5F))
            auxList.add(Tours("Francia",15.99,"tour2",20,4F))
            auxList.add(Tours("Jaco",30.55,"tour3",15,4.5F))
            auxList.add(Tours("Hawaii",80.0,"tour4",50,5F))

            lista = auxList.toMutableList() as ArrayList<Tours>
            adaptador.updateList(lista)
        }


    }

}