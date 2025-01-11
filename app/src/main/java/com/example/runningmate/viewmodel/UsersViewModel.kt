package com.example.runningmate.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.runningmate.RunningMateApplication
import com.example.runningmate.models.UsersResponse
import com.example.runningmate.repositories.UsersRepository
import com.example.runningmate.uiStates.UsersDataStatusUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    // State untuk memantau status data user
    private val _usersDataStatus = MutableStateFlow<UsersDataStatusUIState>(UsersDataStatusUIState.Start)
    val usersDataStatus: StateFlow<UsersDataStatusUIState> get() = _usersDataStatus

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        _usersDataStatus.value = UsersDataStatusUIState.Loading

        viewModelScope.launch {
            try {
                val call = usersRepository.getUsers()

                call.enqueue(object : Callback<UsersResponse> {
                    override fun onResponse(
                        call: Call<UsersResponse>,
                        response: Response<UsersResponse>
                    ) {
                        Log.d("API_Response", "Response: ${response.code()} - ${response.message()}")
                        if (response.isSuccessful) {
                            val users = response.body()
                            if (users != null) {
                                _usersDataStatus.value = UsersDataStatusUIState.Success(users)
                            } else {
                                _usersDataStatus.value = UsersDataStatusUIState.Failed("Response body is null")
                            }
                        } else {
                            _usersDataStatus.value = UsersDataStatusUIState.Failed("Error: ${response.code()} - ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                        Log.e("API_Response", "Failure: ${t.message}")
                        _usersDataStatus.value = UsersDataStatusUIState.Failed(t.message ?: "Unknown error")
                    }
                })
            } catch (e: Exception) {
                Log.e("API_Response", "Exception: ${e.message}")
                _usersDataStatus.value = UsersDataStatusUIState.Failed(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RunningMateApplication)
                val usersRepository = application.container.usersRepository
                UsersViewModel(usersRepository)
            }
        }
    }
}
