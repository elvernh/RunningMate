package com.example.runningmate.services

import com.example.runningmate.models.AchievementModel
import retrofit2.http.GET

interface AchievementAPIService {
    @GET("api/getAchievement")
fun getAchievements(): List<AchievementModel>
}