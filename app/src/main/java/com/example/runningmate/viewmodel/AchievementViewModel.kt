package com.example.runningmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.runningmate.RunningMateApplication
import com.example.runningmate.models.AchievementModel
import com.example.runningmate.repositories.AchievementRepository
import com.example.runningmate.uiStates.AchievementDataStatusUIState
import com.example.runningmate.uiStates.AchievementUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
        // Ubah status UI menjadi Loading
        _achievementDataStatus.value = AchievementDataStatusUIState.Loading
        viewModelScope.launch {
            try {
                // Fetch data dari repository
                val data = achievementRepository.getAchievements()
                // Update UI state menjadi Success dengan data
                _achievementDataStatus.value = AchievementDataStatusUIState.Success(data)
            } catch (e: Exception) {
                // Handle error dan ubah UI state menjadi Failed
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
