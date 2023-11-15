package com.example.movietest.domain.usecase.movie

import com.example.movietest.data.repository.MovieRepository
import com.example.movietest.domain.model.movie.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetUpcomingMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun invoke(): Response<MovieResponse> = withContext(Dispatchers.IO){
        movieRepository.getUpcomingMovies()
    }
}