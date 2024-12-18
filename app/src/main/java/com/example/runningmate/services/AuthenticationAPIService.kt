package com.example.runningmate.services

import com.example.runningmate.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationAPIService {
    @POST("api/register")
    fun register(@Body registerMap: HashMap<String, String>): Call<UserResponse>

    @POST("api/login")
    fun login(@Body loginMap: HashMap<String, String>): Call<UserResponse>
}