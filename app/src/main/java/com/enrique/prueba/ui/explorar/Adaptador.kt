package com.enrique.prueba.ui.explorar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.enrique.prueba.R
import com.enrique.prueba.modelo.Tours
import kotlinx.android.synthetic.main.fragment_explorar.*
import java.util.stream.Collectors

class Adaptador(private val my_list: ArrayList<Tours>): RecyclerView.Adapter<Adaptador.ToursViewHolder>() {

    private val originalList: ArrayList<Tours> = ArrayList()

    init{
        originalList.addAll(my_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToursViewHolder {
        return ToursViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ToursViewHolder, position: Int) {
        val currentTour=my_list[position]
        holder.bind(currentTour)
    }

    override fun getItemCount(): Int {
        return my_list.size
    }
    fun filter(query: String){
        if (query.isNullOrEmpty()) {
            my_list.clear()
            my_list.addAll(originalList)
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                my_list.clear()
                val collect: List<Tours> = originalList.stream()
                        .filter { i -> i.nombre_tour.toLowerCase().contains(query) }
                        .collect(Collectors.toList())
                my_list.addAll(collect)
            }
            else{
                ExplorarFragment.lista.clear()
                for(items in getList()){
                    if(items.nombre_tour.contains(query)) {
                        my_list.add(items)
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    fun getList(): ArrayList<Tours>{
        return my_list
    }
    fun updateList(list: List<Tours>)
    {
        my_list.clear()
        my_list.addAll(list)
        notifyDataSetChanged()
    }


    class ToursViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(tour: Tours){

            val context = itemView.context
            val nombre_tour: TextView = itemView.findViewById(R.id.nombre_tour)
            val precio: TextView = itemView.findViewById(R.id.precio)
            val opiniones: TextView = itemView.findViewById(R.id.cant_opiniones)
            val rating: RatingBar = itemView.findViewById(R.id.ratingBar)
            val imagen: ImageView = itemView.findViewById(R.id.imagen)

            nombre_tour.setText(tour.nombre_tour)
            precio.setText("Precio: $${tour.precio}")
            opiniones.setText("Opiniones: ${tour.cant_comentario}")
            rating.setRating(tour.rating);

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

            Glide.with(itemView.context)
                .load(tour.imagen)
                .into(imagen)

        }
    }
}