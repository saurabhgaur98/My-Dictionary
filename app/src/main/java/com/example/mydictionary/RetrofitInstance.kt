package com.example.mydictionary

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val Base_url = "https://api.dictionaryapi.dev/api/v2/entries/"

    private fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val dictionaryApi : RetrofitInterface = getInstance().create(RetrofitInterface::class.java)

}