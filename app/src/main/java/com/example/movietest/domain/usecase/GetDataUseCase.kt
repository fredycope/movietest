package com.example.movietest.domain.usecase

import com.example.movietest.data.database.dbmodel.MovieTest
import com.example.movietest.data.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDataUseCase (private val dataBaseRepository: DataBaseRepository) {

    suspend fun invoke(typeMovie: Int): List<MovieTest> = withContext(Dispatchers.IO) {
         dataBaseRepository.getData(typeMovie)
    }
}