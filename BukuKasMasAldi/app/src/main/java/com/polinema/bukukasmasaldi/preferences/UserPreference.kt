package com.polinema.bukukasmasaldi.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.polinema.bukukasmasaldi.utils.PreferenceConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference(private val dataStore: DataStore<Preferences>) {

    private val isFirstTimeKey = booleanPreferencesKey(PreferenceConstants.IS_FIRST_TIME_KEY)
    private val usernameKey = stringPreferencesKey(PreferenceConstants.USERNAME_KEY)

    fun getIsFirstTime(): Flow<Boolean> {
        return dataStore.data.map {
            it[isFirstTimeKey] ?: true
        }
    }

    suspend fun saveIsFirstTime(isFirstTime: Boolean) {
        dataStore.edit {
            it[isFirstTimeKey] = isFirstTime
        }
    }

    fun getUsername(): Flow<String?> {
        return dataStore.data.map {
            it[usernameKey]
        }
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit {
            it[usernameKey] = username
        }
    }

}