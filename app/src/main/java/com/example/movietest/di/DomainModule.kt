package com.example.movietest.di

import com.example.movietest.data.repository.DataBaseRepository
import com.example.movietest.data.repository.ServiceRepository
import com.example.movietest.domain.usecase.DeleteDataUseCase
import com.example.movietest.domain.usecase.GetCharacterUseCase
import com.example.movietest.domain.usecase.GetDataUseCase
import com.example.movietest.domain.usecase.SaveDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun providesCharacter(repository: ServiceRepository): GetCharacterUseCase{
        return GetCharacterUseCase(repository)
    }

    @Provides
    fun providesSaveData(dataBaseRepository: DataBaseRepository): SaveDataUseCase{
        return SaveDataUseCase(dataBaseRepository)
    }

    @Provides
    fun providesGetData(dataBaseRepository: DataBaseRepository): GetDataUseCase{
        return GetDataUseCase(dataBaseRepository)
    }

    @Provides
    fun providesDeleteData(dataBaseRepository: DataBaseRepository): DeleteDataUseCase{
        return DeleteDataUseCase(dataBaseRepository)
    }


}