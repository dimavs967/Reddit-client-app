package com.task.reddit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.task.reddit.data.paging.ArticlePagingSource
import com.task.reddit.data.remote.RetrofitApi
import com.task.reddit.data.repository.ArticlesRepository
import com.task.reddit.model.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ArticlesRepository
) : ViewModel() {

//    private val listLiveData = MutableLiveData<ArrayList<ArticleModel>>()

    fun getListLivaData(): LiveData<PagingData<ArticleModel>> {
        return repository.getPagingLiveData()
    }

//    val test: LiveData<PagingData<ArticleModel>> = Pager(
//        config = PagingConfig(5, enablePlaceholders = false)
//    ) {
//        ArticlePagingSource(api)
//    }.liveData


}