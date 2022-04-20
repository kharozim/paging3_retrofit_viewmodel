package com.ozimos.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ozimos.paging3.network.ApiService
import retrofit2.HttpException

class UserPagingResource(
    private val apiService: ApiService,
) : PagingSource<Int, UserResponse>() {
    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        return try {
            val page: Int = params.key ?: 1
            val response = apiService.getUsers(page)

            var nextPage: Int? = null
            var previewsPage: Int? = null

            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    if (data.page ?: 0 <= data.totalPages ?: 0)
                        nextPage = (data.page ?: 1).plus(1)
                    previewsPage = (data.page ?: 1).minus(1)
                }
                LoadResult.Page(
                    data = data?.data ?: emptyList(),
                    prevKey = previewsPage,
                    nextKey = nextPage
                )

            } else {
                LoadResult.Error(HttpException(response))
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

