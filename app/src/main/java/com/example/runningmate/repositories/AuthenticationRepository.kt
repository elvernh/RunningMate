package com.example.runningmate.repositories

import com.example.runningmate.models.UserResponse
import com.example.runningmate.services.AuthenticationAPIService
import retrofit2.Call

//untuk menyimpan logic ketika dikirim data ke backend
interface AuthenticationRepository{
    fun register(username: String, email: String, password: String): Call<UserResponse>
    fun login(email: String, password: String): Call<UserResponse>
}

class NetworkAuthenticationRepository(
    private val authenticationAPIService: AuthenticationAPIService
): AuthenticationRepository{
    override fun register(username: String, email: String, password: String): Call<UserResponse> {
        val registerMap = HashMap<String, String>()
        registerMap["username"] = username
        registerMap["email"] = email
        registerMap["password"] = password

        return authenticationAPIService.register(registerMap)
    }

    override fun login(email: String, password: String): Call<UserResponse> {
        var loginMap = HashMap<String, String>()

        loginMap["email"] = email
        loginMap["password"] = password

        return authenticationAPIService.login(loginMap)
    }
}