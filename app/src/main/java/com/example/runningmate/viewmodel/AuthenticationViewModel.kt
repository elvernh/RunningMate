package com.example.runningmate.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.runningmate.RunningMateApplication
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.models.UserResponse
import com.example.runningmate.repositories.AuthenticationRepository
import com.example.runningmate.uiStates.AuthenticationStatusUIState
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository
):ViewModel() {
//    private val _authenticationUIState =
    var usernameInput by mutableStateOf("")
        private set

    var passwordInput by mutableStateOf("")
        private set

    var emailInput by mutableStateOf("")
        private set

    var dataStatus: AuthenticationStatusUIState by mutableStateOf(AuthenticationStatusUIState.Start)
        private set
    
    val userName = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    fun changeUsernameInput(usernameInput: String) {
        this.usernameInput = usernameInput
    }

    fun changePasswordInput(passwordInput: String) {
        this.passwordInput = passwordInput
    }

    fun changeEmailInput(emailInput: String) {
        this.emailInput = emailInput
    }

    fun resetViewModel(){
        changeUsernameInput("")
        changePasswordInput("")
        changeEmailInput("")
    }


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RunningMateApplication)
                val authenticationRepository = application.container.authenticationRepository
                AuthenticationViewModel(authenticationRepository)
            }
        }
    }

    fun registerUser(navController: NavHostController){
        viewModelScope.launch {
            dataStatus = AuthenticationStatusUIState.Loading

            try {
                val call = authenticationRepository.register(usernameInput, emailInput, passwordInput)
                call.enqueue(object: Callback<UserResponse>{
                    override fun onResponse(call: Call<UserResponse>, res: Response<UserResponse>){
                        if(res.isSuccessful){
                            Log.d("response-data", "RESPONSE DATA: ${res.body()}")

                            dataStatus = AuthenticationStatusUIState.Success(res.body()!!.data)

                            resetViewModel()

                            navController.navigate(PagesEnum.Welcome.name){ //ini hrs di cari tau arti codingannya apa
                                popUpTo(PagesEnum.Register.name){
                                    inclusive = true
                                }
                            }
                        }
                    }

                    override fun onFailure(p0: Call<UserResponse>, p1: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

            }catch (error: IOException){

            }
        }
    }
    fun loginUser(navController: NavHostController){
        viewModelScope.launch {
            dataStatus = AuthenticationStatusUIState.Loading

            try {
                val call = authenticationRepository.login(emailInput, passwordInput)
                call.enqueue(object: Callback<UserResponse>{
                    override fun onResponse(call: Call<UserResponse>, res: Response<UserResponse>){
                        if(res.isSuccessful){
                            Log.d("response-data", "RESPONSE DATA: ${res.body()}")

                            userName.value = res.body()?.data?.username?: "Guest"
                            email.value = res.body()?.data?.email?: "Guest"
                            password.value = res.body()?.data?.password?: "Guest"

                            dataStatus = AuthenticationStatusUIState.Success(res.body()!!.data)

                            resetViewModel()

                            navController.navigate(PagesEnum.Home.name){
                                popUpTo(PagesEnum.Home.name){
                                    inclusive = true
                                }
                            }
                        }
                    }

                    override fun onFailure(p0: Call<UserResponse>, p1: Throwable) {
                        TODO("Not yet implemented")
                    }


                })

            }catch (error: IOException){

            }
        }
    }
}