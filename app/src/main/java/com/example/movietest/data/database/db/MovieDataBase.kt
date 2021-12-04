package com.example.movietest.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movietest.data.database.dbdao.MovieDataDao
import com.example.movietest.data.database.dbmodel.MovieTest

@Database(entities = [MovieTest::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase(){
    abstract fun movieDataDao(): MovieDataDao
}