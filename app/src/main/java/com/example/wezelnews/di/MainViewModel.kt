package com.example.wezelnews.di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wezelnews.domain.app_entry.AppEntryUseCases
import com.example.wezelnews.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases) : ViewModel() {

     var splashcondition by mutableStateOf(true)
        private set

    var startdestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromScreen ->
            if (shouldStartFromScreen) {
                startdestination = Route.NewsNavigation.route
            } else {
                startdestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashcondition = false
        }.launchIn(viewModelScope)
    }
}