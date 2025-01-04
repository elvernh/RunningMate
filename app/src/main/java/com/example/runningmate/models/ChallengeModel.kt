package com.example.runningmate.models

data class ChallengeResponse (
    val data: ChallengeModel
)

data class ChallengeModel(
    val name: String,
    val description: String,
    val image: String
)

data class GetChallengeResponse(
    val data: List<ChallengeModel>
)