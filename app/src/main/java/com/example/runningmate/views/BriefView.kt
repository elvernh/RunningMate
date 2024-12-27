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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.R
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.repositories.FakeAuthenticationRepository

@Composable
fun BriefView(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController
) {
    val customFont = FontFamily(Font(R.font.lexend))
    val primaryColor = Color(0xFF9CFF00)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.backimage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.4f),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(500.dp))

            // Main header text
            Text(
                text = stringResource(id = R.string.briefHeader),
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontFamily = customFont,
            )
            // Sub header text
            Text(
                text = stringResource(id = R.string.briefSubHeader),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 13.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontFamily = customFont,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Get Started Button
            Button(
                onClick = {
                    navController.navigate(PagesEnum.Register.name) {
                        popUpTo(PagesEnum.Home.name) {
                            inclusive = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 34.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "Get Started",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontFamily = customFont
                )
            }

            // Row for already have an account
            Row(Modifier.padding(top = 20.dp)) {
                Text(
                    text = stringResource(id = R.string.already_have_an_account_text),
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontFamily = customFont
                )

                // Sign in link
                Text(
                    text = stringResource(id = R.string.sign_in_text),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = customFont,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navController.navigate(PagesEnum.Login.name) {
                            popUpTo(PagesEnum.Home.name) {
                                inclusive = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreBriefView() {
    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
    BriefView(
        authenticationViewModel = mockViewModel,
        navController = rememberNavController()
    )
}
