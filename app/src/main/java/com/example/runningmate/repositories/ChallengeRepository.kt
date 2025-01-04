package com.example.runningmate.repositories

import com.example.runningmate.models.GetChallengeResponse
import com.example.runningmate.services.ChallengeAPIService
import retrofit2.Call

interface ChallengeRepository {
    fun getChallenges(): Call<GetChallengeResponse>
}
class NetworkChallengeRepository (private val challengeAPIService: ChallengeAPIService): ChallengeRepository{
    override fun getChallenges(): Call<GetChallengeResponse> {
        return challengeAPIService.getChallenges()
    }
}