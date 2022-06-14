package com.task.reddit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.reddit.data.repository.ArticlesRepository
import com.task.reddit.model.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ArticlesRepository
) : ViewModel() {

    private val listLiveData = MutableLiveData<ArrayList<ArticleModel>>()

    fun getListLivaData(): LiveData<ArrayList<ArticleModel>> {
        return listLiveData
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

}