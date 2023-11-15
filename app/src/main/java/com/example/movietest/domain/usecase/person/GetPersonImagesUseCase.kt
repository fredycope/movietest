package com.example.movietest.domain.usecase.person

import com.example.movietest.data.repository.PersonRepository
import com.example.movietest.domain.model.person.ImagesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetPersonImagesUseCase(private val personRepository: PersonRepository) {
    suspend fun invoke(person_id: Long): Response<ImagesResponse> = withContext(Dispatchers.IO){
        personRepository.getPersonImages(person_id = person_id)
    }
}