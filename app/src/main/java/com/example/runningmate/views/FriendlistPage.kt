package com.example.runningmate.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.runningmate.views.components.MenuBar

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
        LazyColumn(
            Modifier
                .weight(1f)
                .padding(horizontal = 30.dp, vertical = 62.dp)
        ) {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                ) {
                    // Back Arrow Icon (Aligned to Start)
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

                    // Friends Text (Centered)
                    Text(
                        text = "Friends",
                        fontFamily = customFont,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            // Handle State
            when (val state = usersState.value) {
                is UsersDataStatusUIState.Success -> {
                    val users = state.data.data
                    if (users != null) {
                        items(users) { user ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Nama User
                                Text(
                                    text = user.username,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    modifier = Modifier.weight(1f)
                                )

                                // Tombol Add Friend
                                Button(
                                    onClick = { /* Action Add Friend */ },
                                    colors = ButtonDefaults.buttonColors( Color.Gray),
                                    modifier = Modifier.size(80.dp, 40.dp)
                                ) {
                                    Text(
                                        text = "Add",
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    } else {
                        item {
                            Text(
                                text = "No friends available.",
                                color = Color.Gray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                            )
                        }
                    }
                }

                is UsersDataStatusUIState.Loading -> {
                    item {
                        Text(
                            text = "Loading Friends...",
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                        )
                    }
                }

                is UsersDataStatusUIState.Failed -> {
                    item {
                        Text(
                            text = "Failed to load friends.",
                            color = Color.Red,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                        )
                    }
                }

                else -> {
                    item {
                        Text(
                            text = "Unexpected state.",
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                        )
                    }
                }
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
