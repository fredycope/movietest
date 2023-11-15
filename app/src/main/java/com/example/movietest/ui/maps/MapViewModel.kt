package com.example.movietest.ui.maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietest.domain.model.map.Location
import com.example.movietest.domain.usecase.maps.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
): ViewModel() {
    var locations = MutableLiveData<List<Location>>()
    fun getLocation() {
        getLocationUseCase.getLocation().addOnSuccessListener{ result ->
            val list = arrayListOf<Location>()
            for (document in result) {
                list.add(
                    Location(document.data["latitud"].toString().toDouble(), document.data["longitud"].toString().toDouble())
                )
            }
            locations.value = list
        }.addOnFailureListener { exception ->
                println( "Error getting documents.")
            }
    }
}