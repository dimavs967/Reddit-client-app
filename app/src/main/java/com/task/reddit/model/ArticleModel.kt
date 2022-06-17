package com.task.reddit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

@Parcelize
data class ImageModel(
    val title: String,
    val url: String
) : Parcelable