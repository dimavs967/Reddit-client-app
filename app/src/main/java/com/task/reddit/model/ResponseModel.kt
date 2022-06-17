package com.task.reddit.model

data class ResponseModel(
    val kind: String,
    val data: DataModel
)

data class DataModel(
    val after: String,
    val children: List<SubDataModel>,
    val before: String
)

data class SubDataModel(
    val kind: String,
    val data: ArticleModel
)
