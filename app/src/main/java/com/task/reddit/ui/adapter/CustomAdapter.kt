package com.task.reddit.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.reddit.R
import com.task.reddit.model.ArticleModel

class CustomAdapter(
    private var onClickListener: (Int) -> Unit = {}
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var list = mutableListOf<ArticleModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun initAdapter(items: List<ArticleModel>) {
        list.clear()
        list.addAll(items)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val author: TextView = view.findViewById(R.id.author)
        private val comments: TextView = view.findViewById(R.id.comments)
        private val created: TextView = view.findViewById(R.id.created)

        private val thumb: ImageView = view.findViewById(R.id.thumb)
        private val preview: ImageView = view.findViewById(R.id.preview)

        fun bind(article: ArticleModel) {

        }
    }

}