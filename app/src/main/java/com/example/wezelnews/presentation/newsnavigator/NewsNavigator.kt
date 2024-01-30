package com.example.wezelnews.presentation.newsnavigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wezelnews.R
import com.example.wezelnews.domain.model.Article
import com.example.wezelnews.navgraph.Route
import com.example.wezelnews.presentation.bookmark.BookmarkScreen
import com.example.wezelnews.presentation.bookmark.BookmarkViewModel
import com.example.wezelnews.presentation.details.DetailsScreen
import com.example.wezelnews.presentation.details.DetailsViewModel
import com.example.wezelnews.presentation.home.HomeScreen
import com.example.wezelnews.presentation.home.HomeViewModel
import com.example.wezelnews.presentation.search.SearchScreen
import com.example.wezelnews.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, "Bookmark")
        )
    }

    val navController = rememberNavController()
    val backstackstate = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backstackstate?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookMarkScreen.route -> 2
        else -> 0
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigatetoTap(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigatetoTap(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigatetoTap(
                            navController = navController,
                            route = Route.BookMarkScreen.route
                        )
                    }
                }
            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewmodel: HomeViewModel = hiltViewModel()
                val articles = viewmodel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles,
                    navigationToSearch = {
                        navigatetoTap(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigatetoDetails = { article ->
                        navigatetoDetail(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigatetoDetails = {
                        navigatetoDetail(
                            navController = navController,
                            article = it
                        )
                    })
            }
            composable(route = Route.DetailsScreen.route) {
                val viewmodel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(article = article, event = viewmodel::onEvent,
                            navigateUp = { navController.navigateUp() })

                    }
            }
            composable(route=Route.BookMarkScreen.route){
                val viewmodel:BookmarkViewModel= hiltViewModel()
                val state=viewmodel.state.value
                BookmarkScreen(state = state, navigatetoDetails ={ article ->
                    navigatetoDetail(navController=navController,article=article)
                } )
            }
        }

    }

}

fun navigatetoTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homescreen ->
            popUpTo(homescreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true


        }
    }
}

fun navigatetoDetail(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}