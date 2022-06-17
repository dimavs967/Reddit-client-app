package com.task.reddit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.task.reddit.data.repository.ArticlesRepository
import com.task.reddit.model.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ArticlesRepository
) : ViewModel() {

    fun getListLivaData(): LiveData<PagingData<ArticleModel>> {
        return repository.getPagingLiveData()
    }

}