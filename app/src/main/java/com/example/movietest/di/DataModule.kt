package com.example.movietest.di

import com.example.movietest.data.database.dbdao.MovieDataDao
import com.example.movietest.data.network.RetrofitService
import com.example.movietest.data.repository.DataBaseRepository
import com.example.movietest.data.repository.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun providesCharacterRepository(retrofitService: RetrofitService): ServiceRepository{
        return ServiceRepository(retrofitService)
    }

    @Provides
    fun providesDataRepository(movieDataDao: MovieDataDao):DataBaseRepository{
        return DataBaseRepository(movieDataDao)
    }
}