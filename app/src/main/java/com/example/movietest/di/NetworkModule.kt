package com.example.movietest.di

import com.example.movietest.data.network.MovieService
import com.example.movietest.data.network.PersonService
import com.example.movietest.utils.Constants
import com.example.movietest.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun providesRetrofit(): Retrofit {
        val httpClient =  OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader("Authorization","Bearer ${Constants.TOKEN}")
            return@Interceptor chain.proceed(builder.build())
        })
        val client: OkHttpClient = httpClient.build()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providePersonService(retrofit: Retrofit): PersonService {
        return retrofit.create(PersonService::class.java)
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}