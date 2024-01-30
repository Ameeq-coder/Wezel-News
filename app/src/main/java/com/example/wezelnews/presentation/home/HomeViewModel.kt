package com.example.wezelnews.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.wezelnews.domain.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject  constructor(
    private val newsUseCase: NewsUseCase
):ViewModel() {

    val news=newsUseCase.getNews(
        sources = listOf("bbc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)

}