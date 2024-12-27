package com.example.runningmate.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.viewmodel.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RunningMateApp(
    navController: NavHostController = rememberNavController(),
    authenticationViewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory),
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    ){
    val token = homeViewModel.token.collectAsState()
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
        composable (route = PagesEnum.Home.name){
            Homepage(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }

        composable(route = PagesEnum.EditProfile.name){
            EditProfile(
                authenticationViewModel = authenticationViewModel,
                navController = navController,
                homeViewModel = homeViewModel,
                token = token.value
            )
        }
        composable(route = PagesEnum.ProfilePage.name){
            ProfileView(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }

        composable(route = PagesEnum.FriendList.name){
            FriendlistPage(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }
    }
}