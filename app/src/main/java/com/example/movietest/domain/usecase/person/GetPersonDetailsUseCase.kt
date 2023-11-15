package com.example.movietest.domain.usecase.person

import com.example.movietest.data.repository.PersonRepository
import com.example.movietest.domain.model.person.PersonDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetPersonDetailsUseCase(private val personRepository: PersonRepository) {
    suspend fun invoke(person_id: Long): Response<PersonDetailsResponse> = withContext(Dispatchers.IO){
        personRepository.getPersonDetails(person_id = person_id)
    }
}