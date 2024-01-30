package com.example.wezelnews.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.wezelnews.onboarding.OnBoardingScreen
import com.example.wezelnews.onboarding.OnBoardingViewModel
import com.example.wezelnews.presentation.bookmark.BookmarkScreen
import com.example.wezelnews.presentation.bookmark.BookmarkViewModel
import com.example.wezelnews.presentation.newsnavigator.NewsNavigator

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::OnEvent)
            }
        }
        navigation(route = Route.NewsNavigation.route, startDestination = Route.NewsNavigatorScreen.route) {
            composable(route = Route.NewsNavigatorScreen.route) {
            NewsNavigator()
            }
        }
    }

}