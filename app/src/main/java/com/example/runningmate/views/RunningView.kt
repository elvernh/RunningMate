package com.example.runningmate.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.google.android.gms.maps.GoogleMap

@Composable
fun RunningView(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController
) {
    val customFont = FontFamily(Font(R.font.lexend))

    Column(
        Modifier.fillMaxSize().padding(vertical = 62.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
            contentAlignment = Alignment.CenterStart // Align content from the start (left side) of the Box
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .clickable(onClick = {
                        navController.popBackStack()
                    })
            )

            // Centered "Run" Text
            Text(
                text = "Run",
                fontFamily = customFont,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center) // Align the "Run" text to the center of the Box
            )
        }


    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun prevRunningView(){
    val mock = AuthenticationViewModel(FakeAuthenticationRepository())
    RunningView(
        mock, rememberNavController()
    )
}