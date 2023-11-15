package com.example.movietest.di

import android.content.Context
import androidx.room.Room
import com.example.movietest.data.database.db.MovieDataBase
import com.example.movietest.data.database.dbdao.MovieDataDao
import com.example.movietest.data.database.dbdao.ProfileDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDataBase {
        return Room.databaseBuilder(
            appContext,
            MovieDataBase::class.java,
            "MovieTest"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideGsDataDao(movieDataBase: MovieDataBase): MovieDataDao {
        return movieDataBase.movieDataDao()
    }

    @Provides
    fun provideProfileDataDao(movieDataBase: MovieDataBase): ProfileDataDao {
        return movieDataBase.profileDataDao()
    }

}