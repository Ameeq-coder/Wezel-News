package com.example.wezelnews.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wezelnews.domain.model.Article
import com.example.wezelnews.navgraph.Route
import com.example.wezelnews.onboarding.Dimens.MediumPadding1
import com.example.wezelnews.presentation.common.ArticleList
import com.example.wezelnews.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigatetoDetails:(Article)->Unit
) {
    val isDarkMode = isSystemInDarkTheme()
    val backgroundColor =
        if (isDarkMode) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surface



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .background(backgroundColor)
            .statusBarsPadding()
    ) {
        SearchBar(text = state.searchquery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) })

        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                modifier = Modifier,
                articles = articles,
                onClick = { navigatetoDetails(it) })
        }
    }


}