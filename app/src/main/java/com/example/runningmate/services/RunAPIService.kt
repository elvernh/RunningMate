package com.example.runningmate.services

import com.example.runningmate.models.RunResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RunAPIService {
    @POST("api/addRun")
    fun addRun(@Body runMap: HashMap<String, Any>): Call<RunResponse>
}