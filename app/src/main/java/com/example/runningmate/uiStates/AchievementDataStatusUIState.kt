package com.example.runningmate.uiStates

import com.example.runningmate.models.GetAchievementsResponse

sealed interface AchievementDataStatusUIState {
    data class Success (val data: GetAchievementsResponse):AchievementDataStatusUIState
    object Start: AchievementDataStatusUIState
    object Loading: AchievementDataStatusUIState
    data class Failed (val errorMessage: String): AchievementDataStatusUIState
}