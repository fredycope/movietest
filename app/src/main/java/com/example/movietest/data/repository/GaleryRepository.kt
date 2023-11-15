package com.example.movietest.data.repository

import com.example.movietest.data.network.FirebaseService

class GaleryRepository(private val firebaseService: FirebaseService) {
    fun saveImage(data: ByteArray) = firebaseService.saveImage(data)
}