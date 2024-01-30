package com.example.wezelnews.domain.news

import com.example.wezelnews.data.local.NewsDao
import com.example.wezelnews.domain.model.Article

class InsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.insert(article)
    }



}