package com.example.runningmate.models

//kenapa UserResponse karena di controllernya pake UserResponse di vscode
data class UserResponse (
    val data: UserModel
)

data class UserModel(
    val username: String,
    val token: String?
)