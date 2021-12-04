package com.example.movietest.data.database.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MovieTest.TABLE_NAME)
class
MovieTest(
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "posterPath") val posterPath: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "backdropPath") val backdropPath: String
) {
        companion object{
            const val TABLE_NAME = "movies"
        }
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "mov_id")
        var movId = 0
}