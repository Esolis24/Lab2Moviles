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
import com.enrique.prueba.R
import com.enrique.prueba.modelo.Tours

class Adaptador(private val my_list: ArrayList<Tours>): RecyclerView.Adapter<Adaptador.ToursViewHolder>() {

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
            rating.setRating(tour.rating)

            if(tour.imagen === "tour1"){
                imagen.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.drawable.tour1))
            }else if(tour.imagen === "tour2"){
                imagen.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.drawable.tour2))
            }else if(tour.imagen === "tour3"){
                imagen.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.drawable.tour3))
            }else if(tour.imagen === "tour4"){
                imagen.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.drawable.tour4))
            }
        }

    }
}