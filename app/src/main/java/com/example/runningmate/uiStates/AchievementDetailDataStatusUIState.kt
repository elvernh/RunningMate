package com.example.runningmate.uiStates

import com.example.runningmate.models.AchievementModel

interface AchievementDetailDataStatusUIState {
    data class Success (val data: AchievementModel):AchievementDetailDataStatusUIState
    object Loading: AchievementDetailDataStatusUIState
    object Start: AchievementDetailDataStatusUIState
    data class Failed (val errorMessage: String): AchievementDetailDataStatusUIState
}