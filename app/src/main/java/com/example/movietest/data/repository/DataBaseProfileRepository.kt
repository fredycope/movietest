package com.example.movietest.data.repository

import com.example.movietest.data.database.dbdao.ProfileDataDao
import com.example.movietest.data.database.dbmodel.Profile

class DataBaseProfileRepository(private val profileDataDao: ProfileDataDao) {
    suspend fun getData() = profileDataDao.getListProfile()

    suspend fun saveData(profile: Profile) = profileDataDao.insert(profile)

    suspend fun deleteData() = profileDataDao.deleteAll()
}