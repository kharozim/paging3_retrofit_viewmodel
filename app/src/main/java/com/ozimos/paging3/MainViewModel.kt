package com.ozimos.paging3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ozimos.paging3.network.ApiClient
import com.ozimos.paging3.network.ApiService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val apiService: ApiService
        get() = ApiClient.apiService
    private val pagingResource: UserPagingResource
        get() = UserPagingResource(apiService)

    private val _listUser = MutableLiveData<PagingData<UserResponse>>()
    val listUser: LiveData<PagingData<UserResponse>>
        get() = _listUser

    fun getListService() {
        viewModelScope.launch {
            val response = Pager(
                config = PagingConfig(pageSize = 3),
                pagingSourceFactory = { pagingResource })

            response.flow.collect {
                _listUser.postValue(it)
            }
        }
    }
}