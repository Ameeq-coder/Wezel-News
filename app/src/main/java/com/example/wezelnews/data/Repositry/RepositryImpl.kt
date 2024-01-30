package com.example.wezelnews.data.Repositry

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wezelnews.data.remote.dto.NewsApi
import com.example.wezelnews.data.remote.dto.NewsPagingSource
import com.example.wezelnews.data.remote.dto.SearchNewsPagingSource
import com.example.wezelnews.domain.Repositry.NewsRepositry
import com.example.wezelnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

class RepositryImpl(
    private val newsApi: NewsApi
):NewsRepositry {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources=sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchnews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery=searchQuery
                    ,newsApi = newsApi,
                    sources=sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}