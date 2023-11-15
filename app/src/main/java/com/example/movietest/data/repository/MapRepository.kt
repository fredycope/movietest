package com.example.movietest.data.repository

import com.example.movietest.data.network.FirebaseService

class MapRepository(private val firebaseService: FirebaseService) {
    fun readLocation() = firebaseService.readLocation()
}