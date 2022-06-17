package com.task.reddit.util

import com.task.reddit.model.ArticleModel
import com.task.reddit.model.SubDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataConverter {

    suspend fun parseList(data: List<SubDataModel>): List<ArticleModel> =
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