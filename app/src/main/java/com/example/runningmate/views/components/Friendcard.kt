package com.example.runningmate.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.runningmate.R

@Composable
fun FriendCard(
    name: String,
    onAddFriendClick: () -> Unit
) {
    val customFont = FontFamily(Font(R.font.lexend))
    val grayColor = Color(0xFF8F8F8F)
    val neonColor = Color(0xFF9CFF00)
    val backgroundColor = Color(0xFF1E1E1E)

    Box(
        modifier = Modifier
            .width(155.dp)
            .height(200.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

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

            Text(
                text = name,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = onAddFriendClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = neonColor,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(32.dp)
            ) {
                Text(
                    text = "Add as friend",
                    fontFamily = customFont,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
