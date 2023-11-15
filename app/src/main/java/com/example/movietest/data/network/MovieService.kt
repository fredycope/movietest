package com.example.movietest.data.network

import com.example.movietest.domain.model.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("3/movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MovieResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

}