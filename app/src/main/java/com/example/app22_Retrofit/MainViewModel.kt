package com.example.app22_Retrofit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var isLoading = mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            // Simulem crides a l'API o Base de Dades
            delay(4000)
            // Un cop tenim les dades...
            isLoading.value = false
        }
    }
}
