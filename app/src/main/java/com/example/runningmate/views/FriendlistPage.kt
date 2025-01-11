package com.example.runningmate.views

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.runningmate.R
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.uiStates.UsersDataStatusUIState
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.viewmodel.UsersViewModel
import com.example.runningmate.views.components.FriendCard
import com.example.runningmate.views.components.MenuBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FriendlistPage(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController,
    usersViewModel: UsersViewModel
) {
    val customFont = FontFamily(Font(R.font.lexend))
    val backgroundColor = Color(0xFF171717)
    val usersState = usersViewModel.usersDataStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // Header
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable(onClick = {
                        navController.popBackStack()
                    })
            )

            Text(
                text = "Friends",
                fontFamily = customFont,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Grid of FriendCards
        when (val state = usersState.value) {
            is UsersDataStatusUIState.Success -> {
                val users = state.data.data
                if (!users.isNullOrEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // Dua kolom
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(users) { user ->
                            FriendCard(
                                name = user.username,
                                onAddFriendClick = { /* Tambahkan logika untuk menambah teman */ }
                            )
                        }
                    }
                } else {
                    Text(
                        text = "No friends available.",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }

            is UsersDataStatusUIState.Loading -> {
                Text(
                    text = "Loading Friends...",
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            is UsersDataStatusUIState.Failed -> {
                Text(
                    text = "Failed to load friends.",
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            else -> {
                Text(
                    text = "Unexpected state.",
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }
        }

        // Menu Bar
        MenuBar(
            selectedMenu = "Friends",
            onMenuClick = { menu ->
                try {
                    val route = PagesEnum.valueOf(menu).name
                    if (route != "Friends") {
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    }
                } catch (e: IllegalArgumentException) {
                    Log.e("Friends", "Invalid menu selected: $menu", e)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            navController = navController
        )
    }
}
