package com.example.movietest.domain.model.movie

import com.google.gson.annotations.SerializedName


data class MovieResponse (

	@SerializedName("page") val page : Int,
	@SerializedName("results") val results : List<Results>,
	@SerializedName("total_pages") val total_pages : Int,
	@SerializedName("total_results") val total_results : Int
)