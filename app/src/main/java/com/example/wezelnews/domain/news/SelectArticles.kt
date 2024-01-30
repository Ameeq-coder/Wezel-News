package com.example.wezelnews.domain.news

import com.example.wezelnews.data.local.NewsDao
import com.example.wezelnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

     operator fun invoke(): Flow<List<Article>>{
       return newsDao.getArticle()
    }

}