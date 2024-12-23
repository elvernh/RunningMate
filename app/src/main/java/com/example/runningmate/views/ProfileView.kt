package com.example.runningmate.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.R
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.repositories.FakeAuthenticationRepository
import com.example.runningmate.viewmodel.AuthenticationViewModel

@Composable
fun ProfileView(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController
) {
    val backgroundColor = Color(0xFF171717)
    val grayColor = Color(0xFF8F8F8F)
    val neon = Color(0xFF9CFF00)
    val customFont = FontFamily(Font(R.font.lexend)) // Custom font declaration
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 30.dp, vertical = 62.dp)
    ) {
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "",
                    tint = Color.White, modifier = Modifier.clickable(
                        onClick = {

                        }
                    )
                )

                Text(
                    text = "Profile",
                    fontFamily = customFont,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(painter = painterResource(id = R.drawable.log), contentDescription = null, tint = Color.White, modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        onClick = {

                        }
                    ))


            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile), contentDescription = null, tint = grayColor, modifier = Modifier.size(100.dp)
                    )
                    Text(
                        text = "25/100 xp",
                        color = grayColor,
                        fontFamily = customFont
                    )
                    Text(
                        text = "RandomUser23",
                        color = Color.White,
                        fontFamily = customFont,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "1",
                                color = neon,
                                fontSize = 20.sp,
                                fontFamily = customFont
                            )
                            Text(
                                text = "Level",
                                color = grayColor,
                                fontSize = 12.sp,
                                fontFamily = customFont
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "5",
                                color = neon,
                                fontSize = 20.sp,
                                fontFamily = customFont
                            )
                            Text(
                                text = "Friends",
                                color = grayColor,
                                fontSize = 12.sp,
                                fontFamily = customFont
                            )
                        }
                    }
                    Row {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = neon),
                            modifier = Modifier
                                .width(190.dp)
                                .height(50.dp).clip(RoundedCornerShape(12.dp)),
                            shape = RectangleShape,

                        ) {
                            Text(
                                text = "Edit Profile",
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = customFont,
                                color = backgroundColor,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prevProfileView() {
    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
    ProfileView(authenticationViewModel = mockViewModel, navController = rememberNavController())
}