package com.example.runningmate.viewmodel

import com.example.runningmate.models.AchievementModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningmate.repositories.AchievementRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AchievementViewModel(private val repository: AchievementRepository) : ViewModel() {

    private val _achievements = MutableStateFlow<List<AchievementModel>>(emptyList())
    val achievements: StateFlow<List<AchievementModel>> get() = _achievements

    fun fetchAchievements() {
        viewModelScope.launch {
            try {
                val data = repository.fetchAchievements()
                _achievements.value = data
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}
