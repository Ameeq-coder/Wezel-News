package com.example.wezelnews.domain.Repositry

import androidx.paging.PagingData
import com.example.wezelnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepositry {
    fun getNews(sources:List<String>):Flow<PagingData<Article>>

    fun searchnews(searchQuery:String,sources:List<String>):Flow<PagingData<Article>>
}