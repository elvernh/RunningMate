package com.example.runningmate.services

import com.example.runningmate.models.GetChallengeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ChallengeAPIService {
    @GET("/api/challenges")
    fun getChallenges(): Call<GetChallengeResponse>
}