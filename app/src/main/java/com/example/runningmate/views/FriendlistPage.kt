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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.runningmate.R
import com.example.runningmate.viewmodel.AuthenticationViewModel

@Composable
fun FriendlistPage(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController,
){
    val customFont = FontFamily(Font(R.font.lexend))
    val backgroundColor = Color(0xFF171717)
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
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



    }
}