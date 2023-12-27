package com.andricohalim.suitmediatest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.retrofit.ApiService

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val userResponse = apiService.getUser(position)
            val dataItems = userResponse.data

            LoadResult.Page(
                data = dataItems,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (dataItems.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}