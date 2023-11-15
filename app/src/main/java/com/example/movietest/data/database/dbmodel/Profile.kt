package com.example.movietest.data.database.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Profile.TABLE_NAME)
class Profile(
    @ColumnInfo(name = "image_profile") val image_profile : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "original_name") val original_name : String,
    @ColumnInfo(name = "overview") val overview : String,
    @ColumnInfo(name = "images_update") val images_updates : String,
) {
    companion object{
        const val TABLE_NAME = "profile"
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profile_id")
    var profileId = 0
}