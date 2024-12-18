package com.example.runningmate.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.enums.PagesEnum

@Composable
fun RunningMateApp(
    navController: NavHostController = rememberNavController()
){
    NavHost(navController = navController, startDestination = PagesEnum.Welcome.name){
        composable(route = PagesEnum.Welcome.name){

        }
    }
}