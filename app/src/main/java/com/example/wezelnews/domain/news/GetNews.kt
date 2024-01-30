package com.example.wezelnews.domain.news

import androidx.paging.PagingData
import com.example.wezelnews.domain.Repositry.NewsRepositry
import com.example.wezelnews.domain.model.Article
import kotlinx.coroutines.flow.Flow
class GetNews(
    private val newsRepositry: NewsRepositry
){
    operator fun invoke(sources: List<String>):Flow<PagingData<Article>>{
  return newsRepositry.getNews(sources = sources)
    }
}