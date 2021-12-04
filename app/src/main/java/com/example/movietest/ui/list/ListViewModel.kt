package com.example.movietest.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietest.data.database.dbmodel.MovieTest
import com.example.movietest.domain.model.Results
import com.example.movietest.domain.usecase.DeleteDataUseCase
import com.example.movietest.domain.usecase.GetCharacterUseCase
import com.example.movietest.domain.usecase.GetDataUseCase
import com.example.movietest.domain.usecase.SaveDataUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val characterUseCase: GetCharacterUseCase,
                                        private val getDataUseCase: GetDataUseCase,
                                        private val saveDataUseCase: SaveDataUseCase,
                                        private val deleteDataUseCase: DeleteDataUseCase): ViewModel() {
    var getDataList = MutableLiveData<List<Results>>()
    fun onCreateCharacter(apikey: String){
        viewModelScope.launch {
          val res =  characterUseCase.invoke(apikey)
            getDataList.postValue(res.results)
        }
    }


    fun getData(){
        viewModelScope.launch {
            val data = getDataUseCase.invoke()
            val arr = arrayListOf<Results>()
            data.map {
                arr.add(Results(false,
                    it.backdropPath,
                arrayListOf(),
                0,
                "",
                "",
                it.originalTitle,
                it.overview,
                0.0,
                it.posterPath,
                it.releaseDate,
                "",
                false,
                0.0,
                0))
            }
            getDataList.postValue(arr)
        }
    }

    fun saveData(movieTest: MovieTest){
        viewModelScope.launch {
            saveDataUseCase.invoke(movieTest)
        }
    }

    fun deleteData(){
        viewModelScope.launch {
            deleteDataUseCase.invoke()
        }
    }
}