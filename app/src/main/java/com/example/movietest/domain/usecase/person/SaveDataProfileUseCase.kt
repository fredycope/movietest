package com.example.movietest.domain.usecase.person

import com.example.movietest.data.database.dbmodel.Profile
import com.example.movietest.data.repository.DataBaseProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveDataProfileUseCase(private val dataBaseProfileRepository: DataBaseProfileRepository) {
    suspend fun invoke(profile: Profile) = withContext(Dispatchers.IO){
        dataBaseProfileRepository.saveData(profile)
    }
}