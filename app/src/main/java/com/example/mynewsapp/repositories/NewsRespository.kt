package com.example.mynewsapp.repositories

import com.example.mynewsapp.api.RetrofitInstance
import com.example.mynewsapp.db.ArticleDatabase

class NewsRespository (
    val db:ArticleDatabase
    ){
    suspend fun getBreakingNews(countryCode:String, pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery : String , pageNumber:Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)
}