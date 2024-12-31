package com.example.runningmate.uiStates

import com.example.runningmate.models.AchievementModel

sealed interface AchievementDataStatusUIState {
    data class Success (val data: List<AchievementModel>):AchievementDataStatusUIState
    object Start: AchievementDataStatusUIState
    object Loading: AchievementDataStatusUIState
    data class Failed (val errorMessage: String): AchievementDataStatusUIState
}