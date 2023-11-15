package com.example.movietest.data.database.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MovieTest.TABLE_NAME)
class MovieTest(
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "type_movie") val type_movie: Int,
) {
        companion object{
            const val TABLE_NAME = "movies"
        }
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "mov_id")
        var movId = 0
}