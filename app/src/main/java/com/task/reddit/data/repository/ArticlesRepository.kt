package com.task.reddit.data.repository

import com.task.reddit.data.remote.RetrofitApi
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val api: RetrofitApi
) {


}