package com.example.runningmate.views

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.viewmodel.AchievementViewModel
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.viewmodel.ChallengeViewModel
import com.example.runningmate.viewmodel.HomeViewModel
import com.example.runningmate.viewmodel.UsersViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RunningMateApp(
    navController: NavHostController = rememberNavController(),
    authenticationViewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory),
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    achievementViewModel: AchievementViewModel = viewModel(factory = AchievementViewModel.Factory),
    challengeViewModel: ChallengeViewModel = viewModel(factory = ChallengeViewModel.Factory),
    usersViewModel: UsersViewModel = viewModel(factory = UsersViewModel.Factory)
    ){
    val localContext = LocalContext.current
    val token = homeViewModel.token.collectAsState()
    NavHost(navController = navController, startDestination = if(token.value != "Unknown" && token.value != "") {
        Log.d("Token", token.toString())
        PagesEnum.Home.name
    } else {
        PagesEnum.Login.name
    }){
        composable(route = PagesEnum.Welcome.name){
            BriefView(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }
        composable(route = PagesEnum.Register.name){
            RegisterView(
                authenticationViewModel = authenticationViewModel,
                navController = navController,
                context = localContext
            )
        }
        composable(route = PagesEnum.Login.name){
            LoginView(
                authenticationViewModel = authenticationViewModel,
                navController = navController,
                context = localContext
            )
        }
        composable (route = PagesEnum.Home.name){
            Homepage(
                homeViewModel = homeViewModel,
                navController = navController,
                challengeViewModel = challengeViewModel,
                token = token.value
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
                homeViewModel = homeViewModel,
                navController = navController
            )
        }

        composable(route = PagesEnum.FriendList.name){
            FriendlistPage(
                authenticationViewModel = authenticationViewModel,
                navController = navController,
                usersViewModel = usersViewModel,
            )
        }
        composable(route = PagesEnum.Record.name){
            RunningView(
                authenticationViewModel = authenticationViewModel,
                navController = navController
            )
        }
        composable(route = PagesEnum.AchievementView.name) {
            AchievementView(
                achievementViewModel = achievementViewModel,
                navController = navController
            )
        }
    }
}