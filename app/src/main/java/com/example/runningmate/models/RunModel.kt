package com.example.runningmate.models

import java.time.LocalDateTime

data class RunResponse(
    val data: RunModel
)

data class RunModel(
    val run_id: Int,                   // This is your primary key (from the database)
    val user_id: Int,                  // Corresponds to the user ID
    val start_time: LocalDateTime,     // Start time of the run
    val timestamp: Int,                // Timestamp of the run
    val avgSpeedInKMH: Float,          // Average speed in km/h
    val progress_distance: Float,      // Progress distance in km
    val progress_duration: Float,      // Progress duration in minutes
    val caloriesBurned: Float,         // Calories burned
    val location: String,              // Location of the run
    val challenge_id: Int?,            // Challenge ID (nullable)
    val runImage: String               // URL or path to the run image
)