package com.example.movietest.domain.usecase

import com.example.movietest.data.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteDataUseCase (private val dataBaseRepository: DataBaseRepository) {
    suspend fun invoke() = withContext(Dispatchers.IO){
        dataBaseRepository.deleteData()
    }
}