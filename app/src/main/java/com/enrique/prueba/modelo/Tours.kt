package com.enrique.prueba.modelo

import com.google.gson.annotations.SerializedName


data class Tours(
                @SerializedName("id")
                var id:Int,
                @SerializedName("nombre_tour")
                 var nombre_tour:String,
                @SerializedName("precio")
                 var precio:Double,
                @SerializedName("imagen")
                 var imagen: String,
                @SerializedName("cant_comentario")
                 var cant_comentario: Int,
                @SerializedName("rating")
                 var rating: Float,
                @SerializedName("fecha_inicial")
                 var fecha_inicial: String,
                @SerializedName("fecha_final")
                 var fecha_final: String) {

}