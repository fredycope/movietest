package com.example.movietest.domain.usecase.maps

import com.example.movietest.data.repository.MapRepository

class GetLocationUseCase(private val mapRepository: MapRepository) {
    fun getLocation() = mapRepository.readLocation()
}