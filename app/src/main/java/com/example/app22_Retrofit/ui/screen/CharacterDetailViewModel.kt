package com.example.app22_Retrofit.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app22_Retrofit.data.Repository
import com.example.app22_Retrofit.data.SWCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel: ViewModel() {
    private val repository = Repository()
    private val _character = MutableStateFlow<SWCharacter?>(null)
    val character: StateFlow<SWCharacter?> = _character.asStateFlow()


    fun getCharacterById(idCharacter:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCharacterById(idCharacter)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _character.value = response.body()
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }
//
//    fun getCharacterByURL(url:String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = repository.getCharacterByUrl(url)
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    _character.value = response.body()
//                } else {
//                    Log.e("Error :", response.message())
//                }
//            }
//        }
//    }
}

