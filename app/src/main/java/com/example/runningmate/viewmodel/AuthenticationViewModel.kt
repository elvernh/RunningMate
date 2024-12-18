package com.example.runningmate.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.runningmate.RunningMateApplication
import com.example.runningmate.repositories.AuthenticationRepository

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository
):ViewModel() {
//    private val _authenticationUIState =
    var usernameInput by mutableStateOf("")
        private set

    var passwordInput by mutableStateOf("")
        private set

    var emailInput by mutableStateOf("")
        private set

    fun changeUsernameInput(usernameInput: String) {
        this.usernameInput = usernameInput
    }

    fun changePasswordInput(passwordInput: String) {
        this.passwordInput = passwordInput
    }

    fun changeEmailInput(emailInput: String) {
        this.emailInput = emailInput
    }

    fun resetViewModel(){
        changeUsernameInput("")
        changePasswordInput("")
        changeEmailInput("")
    }


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RunningMateApplication)
                val authenticationRepository = application.container.authenticationRepository
                AuthenticationViewModel(authenticationRepository)
            }
        }
    }
}