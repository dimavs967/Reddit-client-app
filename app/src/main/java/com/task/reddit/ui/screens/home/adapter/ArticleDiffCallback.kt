package com.task.reddit.ui.screens.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.task.reddit.model.ArticleModel

object ArticleDiffCallback : DiffUtil.ItemCallback<ArticleModel>() {

    override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem == newItem
    }

}