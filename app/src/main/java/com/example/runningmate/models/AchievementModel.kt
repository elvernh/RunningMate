package com.example.runningmate.models

data class AchievementResponse (
    val data: AchievementModel
)

data class AchievementModel(
    val name: String,
    val description: String,
    val image: String
)

data class GetAchievementsResponse(
    val data: List<AchievementModel>
)