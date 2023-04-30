package com.example.mynewsapp.models

import com.example.mynewsapp.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)