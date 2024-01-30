package com.example.wezelnews.domain.news

import com.example.wezelnews.data.local.NewsDao
import com.example.wezelnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(url:String): Article? {
        return newsDao.getArticle(url)
    }
}