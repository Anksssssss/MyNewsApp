package com.example.mynewsapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mynewsapp.models.Article

class MyDiffUtil(
    private val oldList:List<Article>,
    private val newList:List<Article>
    ):DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition].url==newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}