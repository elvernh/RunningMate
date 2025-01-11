package com.example.runningmate.uiStates

import com.example.runningmate.models.GetChallengeResponse
import com.example.runningmate.models.UsersResponse

interface UsersDataStatusUIState {
    data class Success (val data: UsersResponse):UsersDataStatusUIState
    object Start: UsersDataStatusUIState
    object Loading: UsersDataStatusUIState
    data class Failed (val errorMessage: String): UsersDataStatusUIState
}