package com.example.runningmate.repositories

import com.example.runningmate.models.UserModel
import com.example.runningmate.models.UserResponse
import retrofit2.Call
import retrofit2.mock.Calls

//buat bikin fake repo
class FakeAuthenticationRepository : AuthenticationRepository {
    override fun register(username: String, email: String, password: String): Call<UserResponse> {
        // Simulate a successful registration response
        val fakeUserResponse = UserResponse(
            data = UserModel(
                username = username,
                token = "fake_register_token"
            )
        )
        return Calls.response(fakeUserResponse)
    }

    override fun login(email: String, password: String): Call<UserResponse> {
        // Simulate a successful login response
        val fakeUserResponse = UserResponse(
            data = UserModel(
                username = "FakeUser",
                token = "fake_login_token"
            )
        )
        return Calls.response(fakeUserResponse)
    }
}
