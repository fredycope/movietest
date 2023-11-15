package com.example.movietest.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietest.data.database.dbmodel.MovieTest
import com.example.movietest.domain.model.movie.Results
import com.example.movietest.domain.usecase.DeleteDataUseCase
import com.example.movietest.domain.usecase.GetDataUseCase
import com.example.movietest.domain.usecase.SaveDataUseCase
import com.example.movietest.domain.usecase.movie.GetPopularMoviesUseCase
import com.example.movietest.domain.usecase.movie.GetTopRatedMoviesUseCase
import com.example.movietest.domain.usecase.movie.GetUpcomingMoviesUseCase
import com.google.gson.annotations.SerializedName

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
                                        private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
                                        private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
                                        private val getDataUseCase: GetDataUseCase,
                                        private val saveDataUseCase: SaveDataUseCase,
                                        private val deleteDataUseCase: DeleteDataUseCase): ViewModel() {
    var getPopularMovies = MutableLiveData<List<Results>>()
    var getTopRatedMovies = MutableLiveData<List<Results>>()
    var getUpcomingMovies = MutableLiveData<List<Results>>()

    var getPopularMoviesDB = MutableLiveData<List<Results>>()
    var getTopRatedMoviesDB = MutableLiveData<List<Results>>()
    var getUpcomingMoviesDB = MutableLiveData<List<Results>>()

    fun getPopularMovies(){
        viewModelScope.launch {
          val res =  getPopularMoviesUseCase.invoke()
            getPopularMovies.postValue(res.body()?.results)
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch {
            val res =  getTopRatedMoviesUseCase.invoke()
            getTopRatedMovies.postValue(res.body()?.results)
        }
    }

    fun getUpcomingMovies(){
        viewModelScope.launch {
            val res =  getUpcomingMoviesUseCase.invoke()
            getUpcomingMovies.postValue(res.body()?.results)
        }
    }

    fun getData(typeMovie: Int){
        viewModelScope.launch {
            val data = getDataUseCase.invoke(typeMovie)
            val list = arrayListOf<Results>()
            data.map {
                list.add(Results(
                    false,
                    it.backdropPath,
                    arrayListOf(),
                    0,
                    "",
                    it.originalTitle,
                    it.overview,
                    1.0,
                    it.posterPath,
                    it.releaseDate,
                    it.originalTitle,
                    false,
                    0.0,
                    0)
                )
            }
            when (typeMovie) {
                1 -> {getPopularMoviesDB.postValue(list)}
                2 -> {getTopRatedMoviesDB.postValue(list)}
                3 -> {getUpcomingMoviesDB.postValue(list)}
            }
        }
    }

    fun saveData(list: List<Results>, typeMovie: Int){
        viewModelScope.launch {
            list.map {
                val gsTest = MovieTest(it.original_title,it.overview, it.poster_path,it.release_date, it.backdrop_path, typeMovie)
                saveDataUseCase.invoke(gsTest)
            }
        }
    }

    fun deleteData(){
        viewModelScope.launch {
            deleteDataUseCase.invoke()
        }
    }
}