package com.example.movietest.ui.galery

import androidx.lifecycle.ViewModel
import com.example.movietest.domain.usecase.galery.SaveImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GaleryViewModel @Inject constructor(
    private val saveImageUseCase: SaveImageUseCase
): ViewModel() {

    fun saveImage(data: ByteArray) = saveImageUseCase.saveImage(data)

}