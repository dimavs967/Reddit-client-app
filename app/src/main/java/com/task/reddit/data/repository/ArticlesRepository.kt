package com.task.reddit.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.task.reddit.data.paging.ArticlePagingSource
import com.task.reddit.model.ArticleModel
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val pagingSource: ArticlePagingSource
) {

    fun getPagingLiveData(): LiveData<PagingData<ArticleModel>> {
        return Pager(
            config = PagingConfig(5, enablePlaceholders = false)
        ) { pagingSource }.liveData
    }

}