package com.example.app22_Retrofit.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route: NavKey {
    @Serializable
    data object CharacterListScreen: Route()

    @Serializable
    data class CharacterDetailScreen(val id: String): Route()
}