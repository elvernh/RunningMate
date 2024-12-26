package com.example.runningmate.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.viewmodel.AuthenticationViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RunningMateApp(
    navController: NavHostController = rememberNavController(),
    authenticationViewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory)
){
    NavHost(navController = navController, startDestination = PagesEnum.Welcome.name){
        composable(route = PagesEnum.Welcome.name){
            BriefView(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }
        composable(route = PagesEnum.Register.name){
            RegisterView(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }
        composable(route = PagesEnum.Login.name){
            LoginView(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }
        composable (route = PagesEnum.Welcome.name){
            Homepage(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }
    }
}