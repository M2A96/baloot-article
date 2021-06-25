package com.example.ataei.ui.home.articles


data class ArticleItem(
    val author: String,
    val title: String,
    val description: String,
    val articleUrl: String,
    val publishedAt: String,
    val content: String,
    val imageUrl: Int = 0
)