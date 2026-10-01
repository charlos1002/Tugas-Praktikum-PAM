package com.example.tugas2pam

data class NewsArticle(
    val id: Int,
    val title: String,
    val category: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class NewsDetail(
    val id: Int,
    val content: String,
    val author: String
)