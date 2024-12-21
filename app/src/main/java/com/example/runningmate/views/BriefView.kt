package com.example.runningmate.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.R
import com.example.runningmate.repositories.FakeAuthenticationRepository
import com.example.runningmate.viewmodel.AuthenticationViewModel
import androidx.compose.ui.text.style.TextAlign

//yee
@Composable
fun BriefView(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.unsplash_uq2e2v4lhcy), // Replace with your image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xB0000000), // Black with 70% opacity
                            Color(0xB0000000) // Fully transparent
                        )
                    )
                )
        )

        // Overlay content with specific vertical positioning
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 550.dp), // Adjust this value to control vertical position
            horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
        ) {
            Text(
                text = "It’s not just running. It’s building a better you.",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center, // Centers text
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Text(
                text = "Track your progress, challenge your limits, and celebrate every milestone along the way.",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center, // Centers text
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Button(
                onClick = { /* Handle Get Started */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00FF00) // Neon green
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Get Started", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Already have an account? Sign in",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center, // Centers text
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle Sign In */ }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreBriefView() {
    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
    BriefView(authenticationViewModel = mockViewModel, navController = rememberNavController())
}