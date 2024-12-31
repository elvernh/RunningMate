package com.example.runningmate.services

import com.example.runningmate.models.RunResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RunAPIService {
    @POST("api/addRun")
    fun addRun(
        @Header("X-API-TOKEN") token: String,
        @Body runMap: HashMap<String, Any>
    ) : Call<RunResponse>
}