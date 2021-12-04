package com.example.movietest.data.repository

import com.example.movietest.data.network.RetrofitService


class ServiceRepository(private val retrofitService: RetrofitService) {
    suspend fun getCharacter(apikey: String) = retrofitService.getCharacter(apikey)
}