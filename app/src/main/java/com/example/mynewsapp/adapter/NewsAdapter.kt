package com.example.mynewsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapp.R
import com.example.mynewsapp.databinding.ItemArticlePreviewBinding
import com.example.mynewsapp.models.Article

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

     var oldArticleList = emptyList<Article>()

    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding):RecyclerView.ViewHolder(binding.root)

//    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
//        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
//            return oldItem.url==newItem.url
//        }
//
//        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
//            return oldItem==newItem
//        }
//
//    }
//
//    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
         return ArticleViewHolder(ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return oldArticleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = oldArticleList[position]

        holder.binding.apply {
            Glide.with(holder.itemView).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let{it(article)}
        }
    }

    private var onItemClickListener: ((Article)->Unit)?=null

    fun setOnItemClickListener(listener:((Article)->Unit)){
        onItemClickListener = listener
    }

    fun setData(newArticleList :List<Article>){
        val diffUtil = MyDiffUtil(oldArticleList,newArticleList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldArticleList = newArticleList
        diffResults.dispatchUpdatesTo(this)
    }

}