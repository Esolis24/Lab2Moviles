package com.enrique.prueba.ui.explorar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.enrique.prueba.R
import com.enrique.prueba.modelo.Tours
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class Adaptador(private val my_list: ArrayList<Tours>) :
    RecyclerView.Adapter<Adaptador.ToursViewHolder>() {

    private val originalList: ArrayList<Tours> = ArrayList()

    init {
        originalList.addAll(my_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToursViewHolder {
        return ToursViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ToursViewHolder, position: Int) {
        val currentTour = my_list[position]
        holder.bind(currentTour)
    }

    override fun getItemCount(): Int {
        return my_list.size
    }

    fun filter(query: String, f_salida: String, f_regreso: String) {
        val formatter = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
        val tourFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        var initDate: Date
        var finishDate: Date
        if (f_salida.isEmpty())
            initDate = Date(Long.MIN_VALUE)
        else
            initDate = formatter.parse(f_salida)
        if (f_regreso.isEmpty())
            finishDate = Date(Long.MAX_VALUE)
        else
            finishDate = formatter.parse(f_regreso)

        if (query.isNullOrEmpty() && f_salida.isNullOrEmpty() && f_regreso.isNullOrEmpty()) {
            my_list.clear()
            my_list.addAll(originalList)
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                if (my_list == originalList || f_salida.isNullOrEmpty() ||
                    f_regreso.isNullOrEmpty()) {
                    my_list.clear()
                    val collect: List<Tours> = originalList.stream()
                        .filter { i -> i.nombre_tour.toLowerCase().contains(query) }
                        .filter { i ->
                            tourFormatter.parse(i.fecha_inicial).after(initDate)
                        }
                        .filter { i ->
                            tourFormatter.parse(i.fecha_final).before(finishDate)
                        }
                        .collect(Collectors.toList())
                    my_list.addAll(collect)
                } else {
                    val collect: List<Tours> = my_list.stream()
                        .filter { i -> i.nombre_tour.toLowerCase().contains(query) }
                        .filter { i ->
                            tourFormatter.parse(i.fecha_inicial).after(initDate)
                        }
                        .filter { i ->
                            tourFormatter.parse(i.fecha_final).before(finishDate)
                        }
                        .collect(Collectors.toList())
                    my_list.clear()
                    my_list.addAll(collect)
                }
            } else {
                ExplorarFragment.lista.clear()
                for (items in getList()) {
                    if (items.nombre_tour.contains(query)&&
                        tourFormatter.parse(items.fecha_inicial).after(initDate)
                        && tourFormatter.parse(items.fecha_final).before(initDate)) {
                        my_list.add(items)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }


    fun getList(): ArrayList<Tours> {
        return my_list
    }

    fun updateList(list: List<Tours>) {
        my_list.clear()
        my_list.addAll(list)
        notifyDataSetChanged()
    }


    class ToursViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(tour: Tours) {

            val context = itemView.context
            val nombre_tour: TextView = itemView.findViewById(R.id.nombre_tour)
            val precio: TextView = itemView.findViewById(R.id.precio)
            val opiniones: TextView = itemView.findViewById(R.id.cant_opiniones)
            val rating: RatingBar = itemView.findViewById(R.id.ratingBar)
            val imagen: ImageView = itemView.findViewById(R.id.imagen)
            val fecha: TextView = itemView.findViewById(R.id.fecha_tour)

            nombre_tour.text = tour.nombre_tour
            precio.text = "Precio: $${tour.precio}"
            opiniones.text = "Opiniones: ${tour.cant_comentario}"
            rating.rating = tour.rating;
            fecha.text = "${tour.fecha_inicial} - ${tour.fecha_final}"
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

            Glide.with(itemView.context)
                .load(tour.imagen)
                .into(imagen)

        }
    }
}