package com.example.runningmate.services

import com.example.runningmate.models.AchievementModel
import com.example.runningmate.models.GetAchievementsResponse
import retrofit2.Call
import retrofit2.http.GET

interface AchievementAPIService {
    @GET("/achievements")
    fun getAchievements(): Call<GetAchievementsResponse>
}