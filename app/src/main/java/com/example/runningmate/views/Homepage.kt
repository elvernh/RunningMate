package com.example.runningmate.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.R
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.repositories.FakeAuthenticationRepository
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.views.components.MenuBar
import com.example.runningmate.views.components.WeeklySnapshot
import java.time.LocalTime
import androidx.compose.runtime.remember


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Homepage(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController,
) {
    val backgroundColor = Color(0xFF171717)
    val containerColor = Color(0xFF1E1E1E)
    val greetingText = getCurrentGreeting(authenticationViewModel)
    val greetingColor = Color(0xFF8F8F8F)
    val selectedMenu by remember{ mutableStateOf("Home") }
    val customFont = FontFamily(Font(R.font.lexend)) // Custom font declaration

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // Main content inside LazyColumn
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Use weight to allow LazyColumn to take up remaining space above MenuBar
                .padding(horizontal = 30.dp, vertical = 62.dp)
        ) {
            item {
                Row(
                    Modifier.fillMaxWidth().padding(bottom = 34.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = greetingText,
                            color = greetingColor,
                            fontFamily = customFont,
                            fontSize = 14.sp
                        )
                        Text(
                            text = stringResource(id = R.string.ready_to_run),
                            color = Color.White,
                            fontFamily = customFont,
                            fontSize = 18.sp
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.bell),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp).clickable{
                                navController.navigate(PagesEnum.EditProfile.name) {
                                    popUpTo(PagesEnum.EditProfile.name) { inclusive = false }
                                }
                            }
                        )
                    }
                }
            }
            item {
                WeeklySnapshot()
            }
            item {
                Column(Modifier.padding(vertical = 20.dp)) {
                    // Title Row
                    Row(Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                        Text(
                            text = "Achievement Badges",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = customFont,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Main Content Row
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(containerColor, shape = RoundedCornerShape(12.dp))
                            .padding(vertical = 20.dp)
                            .clickable(
                                onClick = {
                                    navController.navigate(PagesEnum.AchievementView.name) {
                                        popUpTo(PagesEnum.AchievementView.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center // Centers content horizontally
                    ) {
                        // Single Column for all content
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Centers content within the Column
                            modifier = Modifier.padding(horizontal = 16.dp) // Add padding for spacing
                        ) {
                            // Image
                            Image(
                                painter = painterResource(id = R.drawable.image_1),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(bottom = 15.dp)
                            )
                            // Title Text
                            Text(
                                text = "Check All of Your Available Achievements!",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = customFont,
                                textAlign = TextAlign.Center // Centers the text
                            )
                            // Subtitle Text
                            Row(Modifier.fillMaxWidth().padding(top = 20.dp ,end = 11.dp), horizontalArrangement = Arrangement.End) {
                                Text(
                                    text = "See All Achievements",
                                    color = Color(0xFF9CFF00),
                                    fontFamily = customFont,
                                    textAlign = TextAlign.End, // Centers the text
                                    fontSize = 10.sp
                                )
                            }

                        }
                    }

                }
            }

        }

        // Fixed Menu Bar
        MenuBar(
            selectedMenu = selectedMenu, // Pass the currently selected menu
            onMenuClick = { menu ->
                if (menu != "Home") { // Avoid navigating to the current screen
                    navController.navigate(menu.lowercase()) {
                        launchSingleTop = true // Avoid multiple instances of the same destination
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(), // Ensures MenuBar spans the full width
            navController = navController // Add navController here
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentGreeting(authenticationViewModel: AuthenticationViewModel): String {
    val userName = authenticationViewModel.userName.value
    val currentHour = LocalTime.now().hour
    val greeting = when (currentHour) {
        in 5..11 -> "Good Morning"
        in 12..17 -> "Good Afternoon"
        in 18..21 -> "Good Evening"
        else -> "Good Night"
    }
    return "$greeting, $userName"
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevHomepage() {
    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
    Homepage(authenticationViewModel = mockViewModel, navController = rememberNavController())
}
