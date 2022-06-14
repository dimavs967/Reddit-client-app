package com.task.reddit.model

data class ResponseModel(
    val kind: String,
    val data: DataModel
)

data class DataModel(
    val children: List<SubDataModel>,
)

data class SubDataModel(
    val kind: String,
    val data: ArticleModel
)
