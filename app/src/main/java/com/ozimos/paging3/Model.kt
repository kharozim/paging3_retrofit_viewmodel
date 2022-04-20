package com.ozimos.paging3

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    val data: List<UserResponse>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    val page: Int? = null
)

data class UserResponse(
    val id: Int? = null,
    @SerializedName("first_name")
    val name: String? = null,
    val avatar: String? = null
)