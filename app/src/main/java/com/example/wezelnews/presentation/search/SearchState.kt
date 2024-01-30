package com.example.wezelnews.presentation.search

import androidx.paging.PagingData
import com.example.wezelnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchquery:String="",
     val articles: Flow<PagingData<Article>>?=null
)  {

}