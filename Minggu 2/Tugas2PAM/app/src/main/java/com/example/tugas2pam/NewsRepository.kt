package com.example.tugas2pam

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.filter

class NewsRepository {

    private val sampleCategories = listOf("Teknologi", "Olahraga", "Politik", "Hiburan")

    // Data berita awal agar layar tidak kosong saat pertama dibaca
    fun getInitialNews(): List<NewsArticle> {
        return listOf(
            NewsArticle(
                id = 101,
                title = "Peluncuran Fitur AI Terbaru di Smartphone",
                category = "Teknologi"
            ),
            NewsArticle(
                id = 102,
                title = "Tim Nasional Menang Tipis di Laga Persahabatan",
                category = "Olahraga"
            ),
            NewsArticle(
                id = 103,
                title = "Pengumuman Kebijakan Ekonomi Terbaru 2026",
                category = "Politik"
            )
        )
    }

    // 1. Flow yang mensimulasikan data berita baru setiap 2 detik
    fun getNewsFeed(): Flow<NewsArticle> = flow {
        var id = 1
        while (true) {
            delay(2000) // Delay 2 detik
            val category = sampleCategories.random()
            val article = NewsArticle(
                id = id,
                title = "Berita $category Terkini #$id",
                category = category
            )
            emit(article)
            id++
        }
    }

    // 2. Filter berita berdasarkan kategori tertentu
    fun getFilteredNewsFeed(categoryFilter: String): Flow<NewsArticle> {
        return getNewsFeed().filter { article ->
            if (categoryFilter == "Semua") true
            else article.category.equals(categoryFilter, ignoreCase = true)
        }
    }

    // 3. Transform data menjadi format siap tampil
    fun getFormattedNewsFeed(categoryFilter: String): Flow<String> {
        return getFilteredNewsFeed(categoryFilter).map { article ->
            "[${article.category.uppercase()}] ${article.title} - Diterbitkan baru saja"
        }
    }

    // 5. Coroutines untuk mengambil detail berita secara async
    suspend fun fetchNewsDetailAsync(articleId: Int): NewsDetail {
        delay(1000) // Simulasi network request async
        return NewsDetail(
            id = articleId,
            content = "Ini adalah isi konten lengkap untuk berita ID #$articleId. Berita ini diambil secara asynchronous menggunakan Coroutines.",
            author = "Tim Redaksi"
        )
    }
}