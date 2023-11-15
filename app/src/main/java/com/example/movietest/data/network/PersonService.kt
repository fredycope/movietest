package com.example.movietest.data.network

import com.example.movietest.domain.model.person.ImagesResponse
import com.example.movietest.domain.model.person.PersonDetailsResponse
import com.example.movietest.domain.model.person.PersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonService {

    /*@GET("4/list/1?page=1")
    suspend fun getCharacter(@Query("api_key") apikey: String): RequestGeneral*/

    @GET("3/person/popular")
    suspend fun getPopularPerson(): Response<PersonResponse>

    @GET("3/person/{person_id}")
    suspend fun getPersonDetails(@Path("person_id") person_id: Long): Response<PersonDetailsResponse>

    @GET("3/person/{person_id}/images")
    suspend fun getPersonImages(@Path("person_id") person_id: Long): Response<ImagesResponse>
}