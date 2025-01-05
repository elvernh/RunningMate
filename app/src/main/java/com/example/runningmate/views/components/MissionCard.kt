package com.example.runningmate.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.runningmate.R

@Composable
fun MissionCard(
    imageRes: String,
    label: String
) {
    val customFont = FontFamily(Font(R.font.lexend))

    Box(
        modifier = Modifier
            .width(155.dp)
            .fillMaxWidth()
            .height(250.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        // Image background
        Image(
            painter = rememberAsyncImagePainter(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        )

        // Black overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)) // Semi-transparent black
                .clip(RoundedCornerShape(10.dp))
        )

        // Text on top of the overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = label,
                color = Color.White,
                fontFamily = customFont,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewMissionCard() {
//    MissionCard(
//        imageRes = R.drawable.image_6,
//        label = "Run For 15 Minutes"
//    )
//}