package com.example.runningmate.repositories

import com.example.runningmate.models.GetChallengeResponse
import com.example.runningmate.models.UsersResponse
import com.example.runningmate.services.ChallengeAPIService
import com.example.runningmate.services.UsersAPIService
import retrofit2.Call

interface UsersRepository {
    fun getUsers(): Call<UsersResponse>
}
class NetworkUsersRepository (private val usersAPIService: UsersAPIService): UsersRepository{
    override fun getUsers(): Call<UsersResponse> {
        return usersAPIService.getUsers()
    }
}