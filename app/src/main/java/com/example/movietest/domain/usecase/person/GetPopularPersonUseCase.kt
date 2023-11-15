package com.example.movietest.domain.usecase.person

import com.example.movietest.data.repository.PersonRepository
import com.example.movietest.domain.model.person.PersonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetPopularPersonUseCase(private val personRepository: PersonRepository) {
    suspend fun invoke(): Response<PersonResponse> = withContext(Dispatchers.IO){
        personRepository.getPopularPerson()
    }
}