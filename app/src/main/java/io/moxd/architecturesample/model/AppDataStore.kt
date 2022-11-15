package io.moxd.architecturesample.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "prefs")

object AppDataStore {
    private const val APP_OPEN_COUNTER_KEY = "app_opened"
    private val APP_OPEN_COUNTER = intPreferencesKey(APP_OPEN_COUNTER_KEY)

    private const val APP_OPEN_COUNTER_DEFAULT_VALUE = 0

    suspend fun incAppOpenedCount(context: Context) {
        context.dataStore.edit { settings ->
            val currentCount = settings[APP_OPEN_COUNTER] ?: APP_OPEN_COUNTER_DEFAULT_VALUE
            settings[APP_OPEN_COUNTER] = currentCount + 1
        }
    }

    fun getAppOpenedCount(context: Context): Flow<Int> {
        return context.dataStore.data.map { settings ->
            settings[APP_OPEN_COUNTER] ?: APP_OPEN_COUNTER_DEFAULT_VALUE
        }
    }
}