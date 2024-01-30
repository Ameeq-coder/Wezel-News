package com.example.wezelnews.data.remote.dto

import com.example.wezelnews.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)