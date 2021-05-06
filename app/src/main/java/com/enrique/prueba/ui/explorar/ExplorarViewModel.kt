package com.enrique.prueba.ui.explorar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enrique.prueba.CustomAdapter
import com.enrique.prueba.R
import kotlinx.android.synthetic.main.fragment_explorar.*

class ExplorarViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        this.value=getApplication<Application>().getString(R.string.query_hint)
    }
    private val _ida_hint = MutableLiveData<String>().apply{
        this.value=getApplication<Application>().getString(R.string.date_init_hint)
    }
    private val _vuelta_hint = MutableLiveData<String>().apply{
        this.value=getApplication<Application>().getString(R.string.date_return_hint)
    }
    val text: LiveData<String> = _text
    val ida: LiveData<String> = _ida_hint
    val vuelta: LiveData<String> = _vuelta_hint

}