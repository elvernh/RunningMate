package com.example.runningmate.repositories

import com.example.runningmate.models.AchievementModel
import com.example.runningmate.models.AchievementResponse
import com.example.runningmate.services.AchievementAPIService
import com.example.runningmate.models.GetAchievementsResponse
import retrofit2.Call


interface AchievementRepository {
    fun getAchievements(): Call<GetAchievementsResponse>
}
class NetworkAchievementRepository (private val achievementAPIService: AchievementAPIService): AchievementRepository{
    override fun getAchievements(): Call<GetAchievementsResponse> {
        return achievementAPIService.getAchievements()
    }
}