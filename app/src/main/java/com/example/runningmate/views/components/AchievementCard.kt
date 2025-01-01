package com.example.runningmate.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runningmate.R

enum class AchievementState {
    LOCKED,
    UNLOCKED
}

// AchievementCard with fixed width and button visible even in locked state
@Composable
fun AchievementCard(
    title: String,
    description: String,
    imageUrl: String,
    state: AchievementState,
    modifier: Modifier = Modifier
) {
    val primaryColor = Color(0xFF1E1E1E)
    val customFont = FontFamily(Font(R.font.lexend))

    Card(
        modifier = modifier
            .padding(bottom = 20.dp, end = 10.dp)
            .width(180.dp) // Set a fixed width for the card
            .height(200.dp), // Optionally, you can fill height or leave it flexible
        shape = RoundedCornerShape(8.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = primaryColor // Set the background color of the card
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Column to hold all content except the overlay
            Column(
                modifier = Modifier
                    .padding(top = 23.dp, bottom = 23.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.image_1),
//                    painter = rememberAsyncImagePainter(imageUrl), // Use imageUrl if you're passing an external URL
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)  // Ensure consistent size for the image
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(10.dp))  // Adjust Spacer height if needed

                // Title Text
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontFamily = customFont,
                    textAlign = TextAlign.Center
                )

                // Description Text
                Text(
                    text = description,
                    fontSize = 8.sp,
                    lineHeight = 12.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 10.dp),
                    fontFamily = customFont,
                    textAlign = TextAlign.Center
                )

                // Button
                Button(
                    onClick = {
                        if (state == AchievementState.UNLOCKED) {
                            // Handle button click only if the achievement is unlocked
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (state == AchievementState.LOCKED) Color.Gray else Color(0xFF9CFF00) // Change color based on state
                    ),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp),
                    modifier = Modifier.height(25.dp),
                ) {
                    Text(
                        text = "Display on profile",
                        color = primaryColor,
                        fontSize = 8.sp,
                        fontFamily = customFont,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Overlay when the achievement is locked
            if (state == AchievementState.LOCKED) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xAA000000), shape = RoundedCornerShape(8.dp))
                        .align(Alignment.Center) // Ensure overlay is centered on the card
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAchievementScreen() {
    AchievementCard(
        title = "First 1km",
        description = "Complete your first one kilometer.",
        imageUrl = "your-image-url-here",  // If using an external URL, pass the URL here
        state = AchievementState.LOCKED
    )
}
