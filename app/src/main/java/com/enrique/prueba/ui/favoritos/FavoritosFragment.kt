package com.enrique.prueba.ui.favoritos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.enrique.prueba.R
import com.enrique.prueba.modelo.Tours
import com.enrique.prueba.repositories.tours.ToursRepository

class FavoritosFragment : Fragment() {

    private lateinit var favoritosViewModel: FavoritosViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoritosViewModel =
                ViewModelProvider(this).get(FavoritosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favoritos, container, false);

        var tours_repo = ToursRepository();
        var tours = MutableLiveData<List<Tours>>();
        tours = tours_repo.tours;
        tours_repo.getAllTours();
        Log.d("FAV_","Repository: ${tours.value}");


        return root
    }
}