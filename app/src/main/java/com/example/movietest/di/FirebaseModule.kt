package com.example.movietest.di

import com.example.movietest.data.network.Firebase
import com.example.movietest.data.network.FirebaseService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class FirebaseModule {

    @Binds
    abstract fun bindFirebaseService(firebase: Firebase): FirebaseService
}