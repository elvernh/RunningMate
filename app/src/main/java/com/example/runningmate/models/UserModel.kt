package com.example.runningmate.models

//kenapa UserResponse karena di controllernya pake UserResponse di vscode
data class UserResponse (
    val data: UserModel
)

data class UserModel(
    val username: String,
    val email: String,
    val password: String,
    val token: String?
)

data class UsersModel(
    val user_id: Int,
    val username: String,
    val email: String,
    val password: String,
    val token: String?
)

data class UsersResponse (
    val data: List<UsersModel>
)