package com.example.runningmate.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.R
import com.example.runningmate.repositories.FakeAuthenticationRepository
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.views.components.MenuBar
import com.example.runningmate.views.components.WeeklySnapshot
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Homepage(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController
) {
    val backgroundColor = Color(0xFF171717)
    val greetingText = getCurrentGreeting()
    val greetingColor = Color(0xFF8F8F8F)
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
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
            item {
                WeeklySnapshot()
            }
        }

        // Fixed Menu Bar
        MenuBar(
            selectedMenu = "Home", // Pass the currently selected menu
            onMenuClick = { menu ->
                if (menu != "Home") { // Avoid navigating to the current screen
                    navController.navigate(menu.lowercase()) {
                        launchSingleTop = true // Avoid multiple instances of the same destination
                    }
                }
            },
            modifier = Modifier.fillMaxWidth() // Ensures MenuBar spans the full width
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentGreeting(): String {
    val currentHour = LocalTime.now().hour
    return when (currentHour) {
        in 5..11 -> "Good Morning, currentUser"
        in 12..17 -> "Good Afternoon, currentUser"
        in 18..21 -> "Good Evening, currentUser"
        else -> "Good Night, currentUser"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prevHomepage() {
    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
    Homepage(authenticationViewModel = mockViewModel, navController = rememberNavController())
}
