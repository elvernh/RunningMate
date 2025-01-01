package com.example.runningmate.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.runningmate.RunningMateApplication
import com.example.runningmate.models.AchievementModel
import com.example.runningmate.models.GetAchievementsResponse
import com.example.runningmate.repositories.AchievementRepository
import com.example.runningmate.uiStates.AchievementDataStatusUIState
import com.example.runningmate.uiStates.AchievementUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AchievementViewModel(
    private val achievementRepository: AchievementRepository
) : ViewModel() {

    // State untuk UI status data
    private val _achievementDataStatus = MutableStateFlow<AchievementDataStatusUIState>(AchievementDataStatusUIState.Start)
    val achievementDataStatus: StateFlow<AchievementDataStatusUIState> get() = _achievementDataStatus

    // State untuk UI dropdown
    private val _uiState = MutableStateFlow(AchievementUIState())
    val uiState: StateFlow<AchievementUIState> get() = _uiState

    init {
        fetchAchievements()
    }

    fun fetchAchievements() {
        // Set the UI status to Loading
        _achievementDataStatus.value = AchievementDataStatusUIState.Loading

        viewModelScope.launch {
            try {
                // Make the API call
                val call = achievementRepository.getAchievements()

                // Execute the call asynchronously
                call.enqueue(object : Callback<GetAchievementsResponse> {
                    override fun onResponse(
                        call: Call<GetAchievementsResponse>,
                        response: Response<GetAchievementsResponse>
                    ) {
                        Log.d("API_Response", "Response: ${response.code()} - ${response.message()}")
                        if (response.isSuccessful) {
                            val achievements = response.body()
                            if (achievements != null) {
                                _achievementDataStatus.value =
                                    AchievementDataStatusUIState.Success(achievements)
                            } else {
                                _achievementDataStatus.value =
                                    AchievementDataStatusUIState.Failed("Response body is null")
                            }
                        } else {
                            _achievementDataStatus.value =
                                AchievementDataStatusUIState.Failed("Error: ${response.code()} - ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<GetAchievementsResponse>, t: Throwable) {
                        _achievementDataStatus.value =
                            AchievementDataStatusUIState.Failed(t.message ?: "Unknown error")
                    }
                })
            } catch (e: Exception) {
                // Handle any exceptions during the call setup
                _achievementDataStatus.value = AchievementDataStatusUIState.Failed(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RunningMateApplication)
                val achievementRepository = application.container.achievementRepository
                AchievementViewModel(achievementRepository)
            }
        }
    }

    fun toggleDropDown() {
        // Toggle status dropdown expanded
        _uiState.value = _uiState.value.copy(
            statusDropDownExpandedValue = !_uiState.value.statusDropDownExpandedValue
        )
    }
}
