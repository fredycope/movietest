package com.example.movietest.domain.model.person

import com.google.gson.annotations.SerializedName

data class ImagesResponse (

	@SerializedName("id") val id : Int,
	@SerializedName("profiles") val profiles : List<Profiles>
)