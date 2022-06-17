package com.task.reddit.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.reddit.model.ArticleModel
import com.task.reddit.util.DataConverter
import retrofit2.HttpException

class ArticlePagingSource(
    private val api: RetrofitApi
) : PagingSource<Int, ArticleModel>() {

    private var after = ""

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        try {
            val page = params.key ?: 0
            val response = api.request(page, after, 10)

            return if (response.isSuccessful) {
                response.body()?.let { after = it.data.after }

                val articles = checkNotNull(response.body()).data.children
                val list = DataConverter().parseList(articles)

                val prevKey = if (page > 0) page - 1 else null
                val nextKey = if (articles.isEmpty()) null else page + 1

                LoadResult.Page(list, prevKey, nextKey)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}