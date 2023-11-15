package com.example.movietest.domain.usecase.person

import com.example.movietest.data.repository.DataBaseProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteDataProfileUseCase(private val dataBaseProfileRepository: DataBaseProfileRepository) {
    suspend fun invoke() = withContext(Dispatchers.IO){
        dataBaseProfileRepository.deleteData()
    }
}