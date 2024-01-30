package com.example.wezelnews.domain.news

import androidx.paging.PagingData
import com.example.wezelnews.data.remote.dto.NewsApi
import com.example.wezelnews.domain.Repositry.NewsRepositry
import com.example.wezelnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SearchNewsCase(
    private val newsRepositry: NewsRepositry
) {
    operator fun invoke(searchQuery:String,sources: List<String>): Flow<PagingData<Article>> {
        return newsRepositry.searchnews(searchQuery = searchQuery,sources = sources)
    }
}