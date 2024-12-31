package com.example.runningmate.views.components

import android.view.ViewDebug.IntToString
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runningmate.R

enum class AchievementState {
    LOCKED,
    UNLOCKED
}

// AchievementCard
@Composable
fun AchievementCard(
    title: String,
    description: String,
    imageUrl: String,
//    state: AchievementState,
    modifier: Modifier = Modifier
) {
    val primaryColor = Color(0xFF)
    Box(
        modifier = modifier
            .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(8.dp))
            .wrapContentHeight()
            .fillMaxWidth() // Make the card take full width
    ) {
        Column(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(), // Center content horizontally
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bell),                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(70.dp)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = description,
                fontSize = 10.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(
                onClick = { /* Handle button click */ },
//                enabled = state == AchievementState.UNLOCKED,
                colors = ButtonDefaults.buttonColors(
//                    containerColor = if (state == AchievementState.LOCKED) Color.Gray else Color(0xFF9CFF00)
                ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(
                    horizontal = 14.dp,
                    vertical = 0.dp // Reduced vertical padding
                )
            ) {
                Text(
                    text =  "Locked" ,
                    color =  Color.White ,
                    fontSize = 13.sp,
                )
            }
        }

//        if (state == AchievementState.LOCKED) {
//            Box(
//                modifier = Modifier
//                    .matchParentSize()
//                    .background(Color(0xAA000000), shape = RoundedCornerShape(8.dp))
//            )
        }
    }



@Preview(showBackground = true)
@Composable
fun PreviewAchievementScreen() {
    Column {
        AchievementCard(
            title = "First 1km",
            description = "Complete your first one kilometer.",
            imageUrl = R.drawable.image_1.toString(),
//            state = AchievementState.LOCKED
        )

        AchievementCard(
            title = "First 5km",
            description = "Complete your first five kilometers.",
            imageUrl = R.drawable.image_1.toString(),
//            state = AchievementState.UNLOCKED
        )
    }
}
