package com.example.wezelnews.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.wezelnews.domain.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
private val useCase: NewsUseCase
) :ViewModel(){

    private val _state= mutableStateOf(SearchState())
     val state:State<SearchState> = _state

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateSearchQuery->{
                _state.value=state.value.copy(searchquery = event.searchQuery)

            }
            is SearchEvent.SearchNews->{
                searchnews()
            }
        }
    }

    private fun searchnews() {
        val articles=useCase.searchnews(
            searchQuery = state.value.searchquery,
            sources = listOf("bbc-news","al-jazeera-english")
        ).cachedIn(viewModelScope)
   _state.value=state.value.copy(articles=articles)
    }


}