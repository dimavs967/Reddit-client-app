package com.task.reddit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.task.reddit.R
import com.task.reddit.model.ArticleModel

class ArticlesAdapter(
    private var onClickListener: (ArticleModel) -> Unit = {},
    diffCallback: DiffUtil.ItemCallback<ArticleModel>
) : PagingDataAdapter<ArticleModel, ArticlesAdapter.ViewHolder>(diffCallback) {

    fun onClickListener(item: (ArticleModel) -> Unit) {
        onClickListener = item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.title)
        private val author: TextView = view.findViewById(R.id.author)
        private val comments: TextView = view.findViewById(R.id.comments)
        private val subReddit: TextView = view.findViewById(R.id.sub_reddit)

        private val thumb: ShapeableImageView = view.findViewById(R.id.thumb)
        private val preview: ShapeableImageView = view.findViewById(R.id.preview)

        private val context = view.context

        fun bind(article: ArticleModel) {
            title.text = article.title
            author.text = article.author
            comments.text = article.numComments
            subReddit.text = article.subRedditNamePrefixed

            Glide
                .with(context)
                .load(article.url)
                .into(preview)

            Glide
                .with(context)
                .load(article.thumbnail)
                .into(thumb)

            preview.setOnClickListener {
                onClickListener(article)
            }
        }
    }

}

