package com.example.movietest.data.database.dbdao

import androidx.room.*
import com.example.movietest.data.database.dbmodel.MovieTest


@Dao
interface MovieDataDao {
    @Insert
    suspend fun insert(movieTest: MovieTest)

    @Update
    suspend fun update(vararg movieTest: MovieTest)

    @Delete
    suspend fun delete(vararg movieTest: MovieTest)

    @Query("DELETE FROM "+MovieTest.TABLE_NAME+" WHERE mov_id = :mId")
    suspend fun deleteId(mId: String)

    @Query("SELECT * FROM " + MovieTest.TABLE_NAME+" WHERE type_movie = :typeMovie")
    suspend fun getListMov(typeMovie: Int): List<MovieTest>

    @Query("DELETE FROM "+MovieTest.TABLE_NAME)
    suspend fun deleteAll()
}