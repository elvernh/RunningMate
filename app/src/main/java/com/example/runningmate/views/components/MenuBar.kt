package com.example.runningmate.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runningmate.R

@Composable
fun MenuBar(
    //tambahin param nantian
){
    val ColorBasic = Color(0xFF1E1E1E)
    val customFont = FontFamily(Font(R.font.lexend)) // Custom font declaration

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(ColorBasic)
                .padding(horizontal = 35.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(painter = painterResource(id = R.drawable.home),
                    contentDescription = null, tint = Color.White)
                Text(
                    text = "Home",
                    color = Color.White,
                    fontFamily = customFont,
                    fontSize = 12.sp
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(painter = painterResource(id = R.drawable.record),
                    contentDescription = null, tint = Color.White)
                Text(
                    text = "Record",
                    color = Color.White,
                    fontFamily = customFont,
                    fontSize = 12.sp
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(painter = painterResource(id = R.drawable.users),
                    contentDescription = null, tint = Color.White)
                Text(
                    text = "Friends",
                    color = Color.White,
                    fontFamily = customFont,
                    fontSize = 12.sp
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(painter = painterResource(id = R.drawable.trello),
                    contentDescription = null, tint = Color.White)
                Text(
                    text = "Profile",
                    color = Color.White,
                    fontFamily = customFont,
                    fontSize = 12.sp
                )
            }
        }
        // Adding the top border to the Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color(0xFFAAAAAA))
                .align(Alignment.TopCenter)
                .padding(horizontal = 35.dp) // Apply the same horizontal padding to align it with Row
        )
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun showMenuBar(){
    MenuBar()
}