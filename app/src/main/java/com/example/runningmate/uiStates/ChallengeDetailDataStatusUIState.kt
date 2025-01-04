package com.example.runningmate.uiStates

import com.example.runningmate.models.ChallengeModel

interface ChallengeDetailDataStatusUIState {
    data class Success (val data: ChallengeModel):ChallengeDetailDataStatusUIState
    object Loading: ChallengeDetailDataStatusUIState
    object Start: ChallengeDetailDataStatusUIState
    data class Failed (val errorMessage: String): ChallengeDetailDataStatusUIState
}