package com.example.runningmate.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.runningmate.views.components.AddFriendButton

@Composable
fun FriendDetail(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController
) {
    val backgroundColor = Color(0xFF171717)
    val customFont = FontFamily(Font(R.font.lexend))
    val grayColor = Color(0xFF8F8F8F)
    val neon = Color(0xFF9CFF00)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 12.dp)
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "",
                    tint = Color.White, modifier = Modifier.clickable(
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                )
            }
        }
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    tint = grayColor,
                    modifier = Modifier
                        .size(80.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "MattWick",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "2",
                        fontSize = 20.sp,
                        fontFamily = customFont,
                        color = neon
                    )
                    Text(
                        text = "Level",
                        fontSize = 14.sp,
                        fontFamily = customFont,
                        color = grayColor
                    )
                }
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "0",
                        fontSize = 20.sp,
                        fontFamily = customFont,
                        color = neon
                    )
                    Text(
                        text = "Friends",
                        fontSize = 14.sp,
                        fontFamily = customFont,
                        color = grayColor
                    )
                }
            }
        }
        item {
            AddFriendButton()
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewFriendDetail() {
//    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
//    FriendDetail(authenticationViewModel = mockViewModel, navController = rememberNavController())
//}