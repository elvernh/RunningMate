package com.example.runningmate.services

import com.example.runningmate.models.UsersResponse
import retrofit2.Call
import retrofit2.http.GET

interface UsersAPIService {
    @GET("/api/users")
    fun getUsers(): Call<UsersResponse>
}