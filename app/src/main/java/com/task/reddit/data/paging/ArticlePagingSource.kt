package com.task.reddit.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.reddit.data.remote.RetrofitApi
import com.task.reddit.model.ArticleModel
import com.task.reddit.model.SubDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(
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
            val page = params.key ?: 1
            val response = api.request(
                page.toString(),
                after, "10"
            )

            return if (response.isSuccessful) {
                response.body()?.let { after = it.data.after }

                val articles = checkNotNull(response.body()).data.children
                val list = parseData(articles)

                val prevKey = if (page > 1) page - 1 else null
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

    private suspend fun parseData(data: List<SubDataModel>): List<ArticleModel> =
        withContext(Dispatchers.IO) {
            val list = mutableListOf<ArticleModel>()

            for (i in data.indices) {
                val current = data[i].data
                list.add(
                    ArticleModel(
                        author = current.author,
                        title = current.title,
                        thumbnail = current.thumbnail,
                        subRedditNamePrefixed = current.subRedditNamePrefixed,
                        numComments = current.numComments,
                        url = current.url
                    )
                )
            }

            return@withContext list
        }

}