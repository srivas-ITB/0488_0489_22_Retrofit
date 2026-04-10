package com.example.app22_Retrofit.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiInterface {
    @GET("people/")
    suspend fun getData(): Response<ListData>

    @GET(value="people/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<SWCharacter>

    @GET()
    suspend fun getCharacterByURL(@Url url: String): Response<SWCharacter>


    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            ).client(client).build()
            return retrofit.create(ApiInterface::class.java)
        }

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    }
}
