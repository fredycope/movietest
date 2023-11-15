package com.example.movietest.di

import com.example.movietest.data.database.dbdao.MovieDataDao
import com.example.movietest.data.database.dbdao.ProfileDataDao
import com.example.movietest.data.network.FirebaseService
import com.example.movietest.data.network.MovieService
import com.example.movietest.data.network.PersonService
import com.example.movietest.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun providesPersonRepository(personService: PersonService): PersonRepository {
        return PersonRepository(personService)
    }

    @Provides
    fun providesMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepository(movieService)
    }


    @Provides
    fun providesDataRepository(movieDataDao: MovieDataDao):DataBaseRepository {
        return DataBaseRepository(movieDataDao)
    }

    @Provides
    fun providesProfileDataRepository(profileDataDao: ProfileDataDao):DataBaseProfileRepository {
        return DataBaseProfileRepository(profileDataDao)
    }

    @Provides
    fun provideGaleryRepository(firebaseService: FirebaseService): GaleryRepository {
        return GaleryRepository(firebaseService)
    }

    @Provides
    fun provideMapRepository(firebaseService: FirebaseService): MapRepository {
        return MapRepository(firebaseService)
    }

}