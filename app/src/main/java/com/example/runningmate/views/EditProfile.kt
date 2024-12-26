package com.example.runningmate.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
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
import com.example.runningmate.repositories.FakeAuthenticationRepository
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.viewmodel.HomeViewModel


@Composable
fun EditProfile(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    token: String
) {
    val backgroundColor = Color(0xFF171717)
    val grayColor = Color(0xFF8F8F8F)
    val darkRed = Color(0xFFa40a0a)
    val customFont = FontFamily(Font(R.font.lexend))
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
                    .padding(bottom = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "",
                    tint = Color.White, modifier = Modifier.clickable(
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                )

                Text(
                    text = "Edit Profile",
                    fontFamily = customFont,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(painter = painterResource(id = R.drawable.log),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(
                            onClick = {
                                homeViewModel.logoutUser(token, navController)
                            }
                        ))
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            tint = grayColor,
                            modifier = Modifier
                                .size(100.dp)
                                .clickable(
                                    onClick = { }
                                )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "null",
                            modifier = Modifier
                                .size(50.dp)
                                .offset(18.dp)
                                .clickable(
                                    onClick = { }
                                )
                        )
                    }
                }
            }
        }
        item {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                thickness = 1.dp,
                color = Color.Gray
            )
            ProfileItem(label = "Name", value = "RandomUser23")
            ProfileItem(label = "Email", value = "RandomUser23@gmail.com")
            ProfileItem(label = "Password", value = "********")
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkRed,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .width(180.dp)
                        .padding(vertical = 18.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Delete Account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = customFont
                    )
                }
            }
        }
    }
}


@Composable
fun ProfileItem(label: String, value: String) {
    val grayColor = Color(0xFF8F8F8F)
    val customFont = FontFamily(Font(R.font.lexend))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = grayColor,
            fontSize = 14.sp,
            fontFamily = customFont,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = customFont,
            modifier = Modifier
                .weight(2f)
                .padding(end = 16.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .size(18.dp)
                .rotate(180f)
                .clickable(
                    onClick = {

                    }
                )
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun EditProfilePreview() {
//    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
//    EditProfile(authenticationViewModel = mockViewModel, navController = rememberNavController())
//}