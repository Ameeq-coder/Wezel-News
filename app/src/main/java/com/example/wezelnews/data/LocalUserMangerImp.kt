package com.example.wezelnews.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.wezelnews.domain.app_entry.LocalUserManger
import com.example.wezelnews.util.Constants
import com.example.wezelnews.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImp(
    private val context: Context
) : LocalUserManger {
    override suspend fun saveEntryApp() {
        context.datastore.edit { settings ->
            settings[PrefrencesKeys.App_ENTRY] = true
        }
    }

    override fun readdata(): Flow<Boolean> {
        return context.datastore.data.map { preferences ->
            preferences[PrefrencesKeys.App_ENTRY] ?: false
        }

    }
}

private val Context.datastore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = USER_SETTINGS
)

private object PrefrencesKeys {
    val App_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}