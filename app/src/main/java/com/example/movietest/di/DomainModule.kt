package com.example.movietest.di

import com.example.movietest.data.repository.*
import com.example.movietest.domain.usecase.DeleteDataUseCase
import com.example.movietest.domain.usecase.GetDataUseCase
import com.example.movietest.domain.usecase.SaveDataUseCase
import com.example.movietest.domain.usecase.galery.SaveImageUseCase
import com.example.movietest.domain.usecase.maps.GetLocationUseCase
import com.example.movietest.domain.usecase.movie.GetPopularMoviesUseCase
import com.example.movietest.domain.usecase.movie.GetTopRatedMoviesUseCase
import com.example.movietest.domain.usecase.movie.GetUpcomingMoviesUseCase
import com.example.movietest.domain.usecase.person.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun providesPopularPerson(repository: PersonRepository): GetPopularPersonUseCase {
        return GetPopularPersonUseCase(repository)
    }

    @Provides
    fun providesPopularMovies(repository: MovieRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(repository)
    }

    @Provides
    fun providesTopRatedMovies(repository: MovieRepository): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCase(repository)
    }

    @Provides
    fun providesUpcomingMovies(repository: MovieRepository): GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCase(repository)
    }

    @Provides
    fun providesPersonDetails(repository: PersonRepository): GetPersonDetailsUseCase {
        return GetPersonDetailsUseCase(repository)
    }

    @Provides
    fun providesPersonImages(repository: PersonRepository): GetPersonImagesUseCase {
        return GetPersonImagesUseCase(repository)
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

    @Provides
    fun providesSaveImage(galeryRepository: GaleryRepository): SaveImageUseCase {
        return SaveImageUseCase(galeryRepository)
    }

    @Provides
    fun provideGetLocation(mapRepository: MapRepository): GetLocationUseCase {
        return GetLocationUseCase(mapRepository)
    }

    @Provides
    fun providesSaveDataProfile(dataBaseProfileRepository: DataBaseProfileRepository): SaveDataProfileUseCase{
        return SaveDataProfileUseCase(dataBaseProfileRepository)
    }

    @Provides
    fun providesGetDataProfile(dataBaseProfileRepository: DataBaseProfileRepository): GetDataProfileUseCase{
        return GetDataProfileUseCase(dataBaseProfileRepository)
    }

    @Provides
    fun providesDeleteDataProfile(dataBaseProfileRepository: DataBaseProfileRepository): DeleteDataProfileUseCase{
        return DeleteDataProfileUseCase(dataBaseProfileRepository)
    }
}