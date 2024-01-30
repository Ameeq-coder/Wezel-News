package com.example.wezelnews.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wezelnews.domain.model.Article
import com.example.wezelnews.domain.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
private val newsUseCase: NewsUseCase
) :ViewModel() {

    var sideeffect by mutableStateOf<String?>(null)

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.UpsertDeleteArticle->{
                viewModelScope.launch {
                    val article=newsUseCase.selectArticle(event.article.url)
                if (article==null){
                    upsertarticle(event.article)
                }else{
                    deletearticle(event.article)
                }
                }
            }
            is DetailsEvent.RemoveSideEffect->{
                sideeffect=null
            }
        }
    }

    private suspend fun upsertarticle(article: Article) {
        newsUseCase.inserarticle(article=article)
    sideeffect="Article Saved"
    }
    private suspend fun deletearticle(article: Article) {
        newsUseCase.deleteArticle(article=article)
        sideeffect="Article Deleted"
    }
}
