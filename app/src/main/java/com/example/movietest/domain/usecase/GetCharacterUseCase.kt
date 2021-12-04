package com.example.movietest.domain.usecase

import com.example.movietest.data.repository.ServiceRepository
import com.example.movietest.domain.model.RequestGeneral
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterUseCase(private val repository: ServiceRepository) {
    suspend fun invoke(apikey: String): RequestGeneral = withContext(Dispatchers.IO){
        repository.getCharacter(apikey)
    }
}