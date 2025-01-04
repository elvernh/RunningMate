package com.example.runningmate.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.runningmate.RunningMateApplication
import com.example.runningmate.models.GetChallengeResponse
import com.example.runningmate.repositories.ChallengeRepository
import com.example.runningmate.uiStates.ChallengeDataStatusUIState
import com.example.runningmate.uiStates.ChallengeDetailDataStatusUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeViewModel (
    private val challegeRepository : ChallengeRepository
): ViewModel(){

    private val _challengeDataStatus = MutableStateFlow<ChallengeDataStatusUIState>(ChallengeDataStatusUIState.Start)
    val challengeDataStatus: StateFlow<ChallengeDataStatusUIState> get() = _challengeDataStatus

    init {
        fetchChallenges()
    }

    fun fetchChallenges() {
        _challengeDataStatus.value = ChallengeDataStatusUIState.Loading

        viewModelScope.launch {
            try {
                val call = challegeRepository.getChallenges()

                call.enqueue(object : Callback<GetChallengeResponse> {
                    override fun onResponse(
                        call : Call<GetChallengeResponse>,
                        response : Response<GetChallengeResponse>
                    ) {
                        Log.d("API_Response", "Response: ${response.code()} - ${response.message()}")
                        if (response.isSuccessful) {
                            val challenges = response.body()
                            if (challenges != null) {
                                _challengeDataStatus.value = ChallengeDataStatusUIState.Success(challenges)
                            } else {
                                _challengeDataStatus.value = ChallengeDataStatusUIState.Failed("Response body is null")
                            }
                        } else {
                            _challengeDataStatus.value = ChallengeDataStatusUIState.Failed("Error: ${response.code()} - ${response.message()}")
                        }
                    }

                    override fun onFailure(call : Call<GetChallengeResponse>, t: Throwable) {
                        _challengeDataStatus.value = ChallengeDataStatusUIState.Failed(t.message ?: "Unknown error")
                    }
                })
            } catch (e : Exception) {
                _challengeDataStatus.value = ChallengeDataStatusUIState.Failed(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RunningMateApplication)
                val challengeRepository = application.container.challengeRepository
                ChallengeViewModel(challengeRepository)
            }
        }
    }
}