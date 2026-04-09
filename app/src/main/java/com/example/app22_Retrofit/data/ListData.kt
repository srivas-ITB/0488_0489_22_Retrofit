package com.example.app22_Retrofit.data

data class ListData(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<SWCharacter>
)