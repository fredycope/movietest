package com.example.movietest.data.database.dbdao

import androidx.room.*
import com.example.movietest.data.database.dbmodel.Profile

@Dao
interface ProfileDataDao {
    @Insert
    suspend fun insert(profile: Profile)

    @Update
    suspend fun update(vararg profile: Profile)

    @Delete
    suspend fun delete(vararg profile: Profile)

    @Query("DELETE FROM "+ Profile.TABLE_NAME+" WHERE profile_id = :mId")
    suspend fun deleteId(mId: String)

    @Query("SELECT * FROM " + Profile.TABLE_NAME)
    suspend fun getListProfile(): List<Profile>

    @Query("DELETE FROM "+ Profile.TABLE_NAME)
    suspend fun deleteAll()
}