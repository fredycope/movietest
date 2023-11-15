package com.example.movietest.data.repository

import com.example.movietest.data.network.MovieService


class MovieRepository(private val movieService: MovieService) {

    suspend fun getPopularMovies() = movieService.getPopularMovies()

    suspend fun getTopRatedMovies() = movieService.getTopRatedMovies()

    suspend fun getUpcomingMovies() = movieService.getUpcomingMovies()
}