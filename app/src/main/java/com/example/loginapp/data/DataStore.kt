package com.example.loginapp.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.runBlocking

class DataStore(context: Context) {

    private val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(name = "my_store")

    private val dataStore = context.dataStore

    suspend fun getAll() = dataStore.data.first()


    fun setLoggedIn(state: Boolean) = runBlocking {
        dataStore.edit { preferences ->
            preferences[PrefKeys.IS_LOGGED_IN] = state
        }
    }

    fun isLoggedIn(): Boolean {
        var value = true
        runBlocking {
            dataStore.data.first {
                value = it[PrefKeys.IS_LOGGED_IN] ?: true
                true
            }
        }
        return value
    }
    fun setEmail(email: String) = runBlocking {
        dataStore.edit { preferences ->
            preferences[PrefKeys.EMAIL_KEY] = email
        }
    }

    fun getEmail(): String {
        var value = ""
        runBlocking {
            dataStore.data.first {
                value = it[PrefKeys.EMAIL_KEY] ?: ""
                true
            }
        }
        return value
    }

    fun setSecondName(name: String) = runBlocking {
        dataStore.edit { preferences ->
            preferences[PrefKeys.NAME_KEY] = name
        }
    }

    fun getSecondName(): String {
        var value = ""
        runBlocking {
            dataStore.data.first {
                value = it[PrefKeys.NAME_KEY] ?: ""
                true
            }
        }
        return value
    }


    private object PrefKeys{
        val NAME_KEY = stringPreferencesKey("name_key")
        val EMAIL_KEY = stringPreferencesKey("email_key")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }
}