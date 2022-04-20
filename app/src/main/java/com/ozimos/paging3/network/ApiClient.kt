package com.ozimos.paging3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    val apiService = getRetrofit().create(ApiService::class.java)
}