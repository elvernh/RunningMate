package com.example.runningmate.views

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.viewmodel.AuthenticationViewModel

@Composable
fun RunningMateApp(
    navController: NavHostController = rememberNavController(),
    authenticationViewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory)
){
    NavHost(navController = navController, startDestination = PagesEnum.Register.name){
        composable(route = PagesEnum.Register.name){
            RegisterView(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }
    }
}