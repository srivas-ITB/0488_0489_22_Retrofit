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

class CharacterListViewModel: ViewModel() {
    private val repository = Repository()
    private val _characters = MutableStateFlow<List<SWCharacter>>(emptyList())
    val characters: StateFlow<List<SWCharacter>> = _characters.asStateFlow()

    // Control de paginación
    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false

    init {
        getCharacters_simplePage()
    }

    fun getCharacters_simplePage() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _characters.value = response.body()?.results!!
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }
}

