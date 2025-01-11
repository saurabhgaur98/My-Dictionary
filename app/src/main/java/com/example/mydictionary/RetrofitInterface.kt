package com.example.mydictionary

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {

    @GET("en/{word}")
    suspend fun getMeaning(@Path("word") word: String): Response<List<MeaningModel>>
}