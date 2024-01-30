package com.example.wezelnews.domain.app_entry

import kotlinx.coroutines.flow.Flow

interface LocalUserManger {
    suspend fun saveEntryApp()
    fun readdata(): Flow<Boolean>
}