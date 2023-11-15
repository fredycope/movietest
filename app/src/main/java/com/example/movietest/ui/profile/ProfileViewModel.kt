package com.example.movietest.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietest.data.database.dbmodel.Profile
import com.example.movietest.domain.model.person.Profiles
import com.example.movietest.domain.model.person.Results
import com.example.movietest.domain.usecase.person.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getPopularPersonUseCase: GetPopularPersonUseCase,
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase,
    private val getPersonImagesUseCase: GetPersonImagesUseCase,

    private val saveDataProfileUseCase: SaveDataProfileUseCase,
    private val getDataProfileUseCase: GetDataProfileUseCase,
    private val deleteDataProfileUseCase: DeleteDataProfileUseCase
) : ViewModel() {
    var getPopularPerson = MutableLiveData<List<Results>>()
    var getPersonImage = MutableLiveData<List<Profiles>>()
    var getProfile = MutableLiveData<Profile>()

    fun getPopularPerson() {
        viewModelScope.launch {
            val res = getPopularPersonUseCase.invoke()
            getPopularPerson.postValue(res.body()?.results)
        }
    }

    fun getPersonImages(person_id: Long) {
        viewModelScope.launch {
            val res = getPersonImagesUseCase.invoke(person_id = person_id)
            getPersonImage.postValue(res.body()?.profiles)
        }
    }

    fun saveProfile(profile: Profile){
        viewModelScope.launch {
            saveDataProfileUseCase.invoke(profile)
        }
    }
    fun deleteData(){
        viewModelScope.launch {
            deleteDataProfileUseCase.invoke()
        }
    }

    fun getData(){
        viewModelScope.launch {
           val res = getDataProfileUseCase.invoke()
            getProfile.postValue(res[0])
        }
    }

}