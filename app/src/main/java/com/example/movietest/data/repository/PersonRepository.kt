package com.example.movietest.data.repository

import com.example.movietest.data.network.PersonService


class PersonRepository(private val personService: PersonService) {

    suspend fun getPopularPerson() = personService.getPopularPerson()

    suspend fun getPersonDetails(person_id: Long) = personService.getPersonDetails(person_id = person_id)

    suspend fun getPersonImages(person_id: Long) = personService.getPersonImages(person_id = person_id)
}