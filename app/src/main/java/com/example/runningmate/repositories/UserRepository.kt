package com.example.runningmate.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.runningmate.models.GeneralResponseModel
import com.example.todolistapp.services.UserAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Call

interface UserRepository{
    val currentUserToken: Flow<String>
    val currentUsername: Flow<String>
    val currentEmail: Flow<String>
    val currentPassword: Flow<String>
    fun logout(token: String): Call<GeneralResponseModel>

    suspend fun saveUserToken(token: String)
    suspend fun savePassword(password: String)
    suspend fun saveUsername(username: String)
    suspend fun saveEmail(email: String)
}

class NetworkUserRepository(
    private val userDataStore: DataStore<Preferences>,
    private val userAPIService: UserAPIService
): UserRepository {
    private companion object {
        val USER_TOKEN = stringPreferencesKey("token")
        val USERNAME = stringPreferencesKey("username")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
    }

    override val currentUserToken: Flow<String> = userDataStore.data.map { preferences ->
        preferences[USER_TOKEN] ?: "Unknown"
    }

    override val currentUsername: Flow<String> = userDataStore.data.map { preferences ->
        preferences[USERNAME] ?: "Unknown"
    }

    override val currentEmail: Flow<String> = userDataStore.data.map { preferences ->
        preferences[EMAIL] ?: "Unknown"
    }

    override val currentPassword: Flow<String> = userDataStore.data.map { preferences ->
        preferences[PASSWORD] ?: "Unknown"
    }

    override suspend fun saveUserToken(token: String) {
        userDataStore.edit { preferences ->
            preferences[USER_TOKEN] = token
        }
    }

    override suspend fun saveUsername(username: String) {
        userDataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    override suspend fun savePassword(password: String) {
        userDataStore.edit { preferences ->
            preferences[PASSWORD] = password
        }
    }

    override suspend fun saveEmail(email: String)
    {
        userDataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }
    override fun logout(token: String): Call<GeneralResponseModel> {
        return userAPIService.logout(token)
    }
}