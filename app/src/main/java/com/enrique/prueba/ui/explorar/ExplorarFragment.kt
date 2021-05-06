package com.enrique.prueba.ui.explorar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enrique.prueba.CustomAdapter
import com.enrique.prueba.R
import kotlinx.android.synthetic.main.fragment_explorar.*

class ExplorarFragment : Fragment() {

    private lateinit var explorarViewModel: ExplorarViewModel
    private var customAdapter: CustomAdapter?=null
    companion object{
        var myList: ArrayList<Tour> = arrayListOf()
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        explorarViewModel =
                ViewModelProvider(this).get(ExplorarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explorar, container, false)

        explorarViewModel.text.observe(viewLifecycleOwner, {
            explorar_search.queryHint=it
        })
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("testeo","Entramos")
        super.onViewCreated(view, savedInstanceState)
        customAdapter= CustomAdapter(myList)
        var layoutManager: RecyclerView.LayoutManager?=
                LinearLayoutManager(this.context,
                        RecyclerView.VERTICAL, false)
              my_recycler_view.layoutManager=layoutManager
               my_recycler_view.adapter=customAdapter
            if(customAdapter!!.itemCount<=0) {
                var aux: ArrayList<Tour> =arrayListOf()
                val tour1 = Tour("Aurora Boreal, Finlandia", 1250.50f, "tour1.jpg")
                val tour2 = Tour("Isla del Coco, Costa Rica", 2999.99f, "tour2.jpg")
                val tour3 = Tour("Berna, Suiza", 1600.33f, "tour3.jpg")
                aux.add(tour1)
                aux.add(tour2)
                aux.add(tour3)
                myList= aux.toMutableList() as ArrayList<Tour>
                customAdapter?.updateTours(myList)
            }

    }
}
