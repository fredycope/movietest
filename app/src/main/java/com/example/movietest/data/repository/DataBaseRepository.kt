package com.example.movietest.data.repository

import com.example.movietest.data.database.dbdao.MovieDataDao
import com.example.movietest.data.database.dbmodel.MovieTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataBaseRepository(private val movieDataDao: MovieDataDao) {
    suspend fun getData(typeMovie: Int) = movieDataDao.getListMov(typeMovie)

    suspend fun saveData(movieTest: MovieTest) = movieDataDao.insert(movieTest)

    suspend fun deleteData() = movieDataDao.deleteAll()
}