package com.example.runningmate.views

import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.runningmate.uiStates.ChallengeDataStatusUIState
import com.example.runningmate.viewmodel.ChallengeViewModel
import com.example.runningmate.viewmodel.HomeViewModel
import com.example.runningmate.views.components.MissionCard


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Homepage(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    challengeViewModel: ChallengeViewModel,
    token: String
) {
    val backgroundColor = Color(0xFF171717)
    val containerColor = Color(0xFF1E1E1E)
    val userName by homeViewModel.username.collectAsState()
    val currentHour = LocalTime.now().hour
    val greeting = when (currentHour) {
        in 5..11 -> "Good Morning"
        in 12..17 -> "Good Afternoon"
        in 18..21 -> "Good Evening"
        else -> "Good Night"
    }
    val greetingText = "$greeting, $userName"
    val greetingColor = Color(0xFF8F8F8F)
    val selectedMenu by remember{ mutableStateOf("Home") }
    val customFont = FontFamily(Font(R.font.lexend)) // Custom font declaration
    val challengeDataState by challengeViewModel.challengeDataStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(start = 30.dp,end = 30.dp, top = 62.dp)
        ) {
            // Greeting Section
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
                            modifier = Modifier.size(28.dp).clickable {
                                navController.navigate(PagesEnum.EditProfile.name) {
                                    popUpTo(PagesEnum.EditProfile.name) { inclusive = false }
                                }
                            }
                        )
                    }
                }
            }

            // Weekly Snapshot Section
            item {
                WeeklySnapshot()
            }

            // Challenges Section
            item {
                Column(Modifier.padding(vertical = 20.dp)) {
                    Row(Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                        Text(
                            text = "Challenges",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = customFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    when (challengeDataState) {
                        is ChallengeDataStatusUIState.Success -> {
                            val challenges =
                                (challengeDataState as ChallengeDataStatusUIState.Success).data.data

                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(challenges) { challenge ->
                                    MissionCard(
                                        imageRes = R.drawable.image_6,
                                        label = challenge.name
                                    )
                                }
                            }
                        }
                        is ChallengeDataStatusUIState.Loading -> {
                            Text(
                                text = "Loading Challenges...",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        is ChallengeDataStatusUIState.Failed -> {
                            Text(
                                text = "Failed to load challenges.",
                                color = Color.Red,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        else -> Unit
                    }
                }
            }

            // Achievement Badges Section
            item {
                Column(Modifier.padding(vertical = 20.dp)) {
                    Row(Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                        Text(
                            text = "Achievement Badges",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = customFont,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(12.dp))
                            .padding(vertical = 20.dp)
                            .clickable {
                                navController.navigate(PagesEnum.AchievementView.name) {
                                    popUpTo(PagesEnum.AchievementView.name) { inclusive = true }
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image_1),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(bottom = 15.dp)
                            )
                            Text(
                                text = "Check All of Your Available Achievements!",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = customFont,
                                textAlign = TextAlign.Center
                            )
                            Row(
                                Modifier.fillMaxWidth().padding(top = 20.dp, end = 11.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    text = "See All Achievements",
                                    color = Color(0xFF9CFF00),
                                    fontFamily = customFont,
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
            selectedMenu = selectedMenu,
            onMenuClick = { menu ->
                try {
                    val route = PagesEnum.valueOf(menu).name
                    if (route != "Home") {
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    }
                } catch (e: IllegalArgumentException) {
                    Log.e("Homepage", "Invalid menu selected: $menu", e)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            navController = navController
        )
    }
}




//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PrevHomepage() {
//    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
//    val challengeViewModel = ChallengeViewModel()
//    Homepage(authenticationViewModel = mockViewModel, navController = rememberNavController(), challengeViewModel = challengeViewModel)
//}
