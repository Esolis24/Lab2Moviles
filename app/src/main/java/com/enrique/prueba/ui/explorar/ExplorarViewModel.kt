package com.enrique.prueba.ui.explorar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enrique.prueba.modelo.Tours
import com.enrique.prueba.repositories.tours.ToursRepository

class ExplorarViewModel : ViewModel() {
    var tours = MutableLiveData<List<Tours>>();
    var toursRepository = ToursRepository();

    init{
        tours = toursRepository.tours;
    }

    fun loadTours(){
        toursRepository.getAllTours();
    }

}