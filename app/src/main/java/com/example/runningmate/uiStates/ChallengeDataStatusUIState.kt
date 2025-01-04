package com.example.runningmate.uiStates

import com.example.runningmate.models.GetChallengeResponse

interface ChallengeDataStatusUIState {
    data class Success (val data: GetChallengeResponse):ChallengeDataStatusUIState
    object Start: ChallengeDataStatusUIState
    object Loading: ChallengeDataStatusUIState
    data class Failed (val errorMessage: String): ChallengeDataStatusUIState
}