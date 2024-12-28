package com.example.runningmate.repositories

import com.example.runningmate.models.AchievementModel
import com.example.runningmate.services.AchievementAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AchievementRepository() {
    private val apiService: AchievementAPIService = TODO()
    suspend fun fetchAchievements(): List<AchievementModel> {
        return apiService.getAchievements()
    }
}