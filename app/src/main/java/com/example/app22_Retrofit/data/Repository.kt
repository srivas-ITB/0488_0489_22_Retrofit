package com.example.app22_Retrofit.data

class Repository {
    val apiInterface = ApiInterface.Companion.create()
    suspend fun getAllCharacters() = apiInterface.getData()

    suspend fun getAllCharacters(page: Int) = apiInterface.getData("people/?page=$page")

}