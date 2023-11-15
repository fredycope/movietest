package com.example.movietest.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movietest.data.database.dbdao.MovieDataDao
import com.example.movietest.data.database.dbdao.ProfileDataDao
import com.example.movietest.data.database.dbmodel.MovieTest
import com.example.movietest.data.database.dbmodel.Profile

@Database(entities = [MovieTest::class, Profile::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase(){
    abstract fun movieDataDao(): MovieDataDao
    abstract fun profileDataDao(): ProfileDataDao
}