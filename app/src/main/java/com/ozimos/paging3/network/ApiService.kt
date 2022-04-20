package com.ozimos.paging3.network

import com.ozimos.paging3.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): Response<BaseResponse>
}