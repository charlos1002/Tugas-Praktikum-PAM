package com.example.tugas2pam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val repository = NewsRepository()

    // Mengisi initial state dengan berita awal
    private val _newsList = MutableStateFlow<List<NewsArticle>>(repository.getInitialNews())
    val newsList: StateFlow<List<NewsArticle>> = _newsList.asStateFlow()

    // 4. StateFlow untuk menyimpan jumlah berita yang sudah dibaca
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    private val _selectedCategory = MutableStateFlow("Semua")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _selectedDetail = MutableStateFlow<NewsDetail?>(null)
    val selectedDetail: StateFlow<NewsDetail?> = _selectedDetail.asStateFlow()

    private val _isLoadingDetail = MutableStateFlow(false)
    val isLoadingDetail: StateFlow<Boolean> = _isLoadingDetail.asStateFlow()

    init {
        startCollectingNews()
    }

    private fun startCollectingNews() {
        viewModelScope.launch {
            repository.getNewsFeed().collect { newArticle ->
                val currentCategory = _selectedCategory.value
                if (currentCategory == "Semua" || newArticle.category == currentCategory) {
                    _newsList.value = listOf(newArticle) + _newsList.value
                }
            }
        }
    }

    fun setCategoryFilter(category: String) {
        _selectedCategory.value = category
        val initial = repository.getInitialNews()
        _newsList.value = if (category == "Semua") initial else initial.filter { it.category == category }
    }

    fun markAsRead(articleId: Int) {
        _readCount.value += 1

        // 5. Coroutines untuk mengambil detail berita secara async
        viewModelScope.launch {
            _isLoadingDetail.value = true
            val detail = repository.fetchNewsDetailAsync(articleId)
            _selectedDetail.value = detail
            _isLoadingDetail.value = false
        }
    }
}