package com.example.movietest.domain.usecase.galery

import com.example.movietest.data.repository.GaleryRepository

class SaveImageUseCase(private val galeryRepository: GaleryRepository) {
    fun saveImage(data: ByteArray) = galeryRepository.saveImage(data)
}