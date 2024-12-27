package com.example.runningmate.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.runningmate.RunningMateApplication
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.models.ErrorModel
import com.example.runningmate.models.GeneralResponseModel
import com.example.runningmate.repositories.UserRepository
import com.example.runningmate.uiStates.HomeUIState
import com.example.runningmate.uiStates.StringDataStatusUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeViewModel(
    private val userRepository: UserRepository,
): ViewModel() {
    private val _homeUIState = MutableStateFlow(HomeUIState())

    var logoutStatus: StringDataStatusUIState by mutableStateOf(StringDataStatusUIState.Start)
        private set


    val username: StateFlow<String> = userRepository.currentUsername.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val email: StateFlow<String> = userRepository.currentEmail.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val password: StateFlow<String> = userRepository.currentPassword.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val token: StateFlow<String> = userRepository.currentUserToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    fun logoutUser(token: String, navController: NavHostController) {
        viewModelScope.launch {
            logoutStatus = StringDataStatusUIState.Loading

            Log.d("token-logout", "LOGOUT TOKEN: $token")

            try {
                val call = userRepository.logout(token)

                call.enqueue(object : Callback<GeneralResponseModel> {
                    override fun onResponse(call: Call<GeneralResponseModel>, res: Response<GeneralResponseModel>) {
                        if (res.isSuccessful) {
                            logoutStatus = StringDataStatusUIState.Success(data = res.body()?.data ?: "Logout successful")

                            saveUsernameTokenEmailPassword("Unknown", "Unknown", "Unknown", "Unknown")

                            navController.navigate(PagesEnum.Login.name) {
                                popUpTo(PagesEnum.Home.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            // Parse the error response safely
                            val errorBody = res.errorBody()?.charStream()?.let {
                                Gson().fromJson(it, ErrorModel::class.java)
                            }
                            val errorMessage = errorBody?.errors ?: "Unknown error occurred"

                            logoutStatus = StringDataStatusUIState.Failed(errorMessage)
                            Log.d("logout-error", "Error message: $errorMessage")
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        val errorMessage = t.localizedMessage ?: "Network error occurred"
                        logoutStatus = StringDataStatusUIState.Failed(errorMessage)
                        Log.d("logout-failure", errorMessage)
                    }
                })
            } catch (error: IOException) {
                val errorMessage = error.localizedMessage ?: "Unexpected error occurred"
                logoutStatus = StringDataStatusUIState.Failed(errorMessage)
                Log.d("logout-error", errorMessage)
            }
        }
    }

    fun saveUsernameTokenEmailPassword(token: String, username: String, email: String, password: String) {
        viewModelScope.launch {
            userRepository.saveUserToken(token)
            userRepository.saveUsername(username)
            userRepository.saveEmail(email)
            userRepository.savePassword(password)
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RunningMateApplication)
                val userRepository = application.container.userRepository
                HomeViewModel(userRepository)
            }
        }
    }
}