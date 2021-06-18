package com.enrique.prueba.modelo

import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("id")
        val id:Int?,
        @SerializedName("user_id")
                 val user_id:String,
        @SerializedName("name")
                 val name:String,
        @SerializedName("username")
                 val username:String,
        @SerializedName("email")
                 val email:String,
        @SerializedName("pass")
                  val pass:String,
        @SerializedName("pais")
                  val pais:String,
        @SerializedName("nacimiento")
                  val nacimiento:String,
)