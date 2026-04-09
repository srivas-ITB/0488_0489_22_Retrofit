package com.example.app22_Retrofit.data

class Repository {
    val apiInterface = ApiInterface.Companion.create()
    suspend fun getAllCharacters() = apiInterface.getData()

    suspend fun getCharacterById(id:Int) = apiInterface.getCharacterById(id)
//
//    suspend fun getCharacterByUrl(url:String) = apiInterface.getCharacterByURL(url)
}