package com.example.wezelnews.presentation.bookmark

import androidx.lifecycle.ViewModel
import com.example.wezelnews.domain.model.Article

data class BookMarkState(
    val articles:List<Article> = emptyList()
) :ViewModel() {



}