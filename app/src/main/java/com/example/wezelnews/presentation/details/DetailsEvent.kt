package com.example.wezelnews.presentation.details

import com.example.wezelnews.domain.model.Article

sealed class DetailsEvent {
data class UpsertDeleteArticle(val article: Article):DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}