package com.task.reddit.model

import com.google.gson.annotations.SerializedName

data class ArticleModel(
    val author: String,
    val title: String,
    val thumbnail: String,
    @SerializedName("subreddit_name_prefixed")
    val subRedditNamePrefixed: String,
    @SerializedName("num_comments")
    val numComments: String,
    val url: String
)