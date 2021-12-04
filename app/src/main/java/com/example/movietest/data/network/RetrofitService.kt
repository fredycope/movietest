package com.example.movietest.data.network

import com.example.movietest.domain.model.RequestGeneral
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("list/1?page=1")
    suspend fun getCharacter(@Query("api_key") apikey: String): RequestGeneral

}