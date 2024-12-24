package com.example.runningmate.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavHostController
import com.example.runningmate.R
import com.example.runningmate.enums.PagesEnum

@Composable
fun BackButton(
    navController: NavHostController
){
    val customFont = FontFamily(Font(R.font.lexend)) // Custom font declaration

    Row(Modifier.fillMaxWidth().clickable(
        onClick = {
            navController.popBackStack()
        }
    ),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "", tint = Color.White)
        Text(
            text = "Back",
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontFamily = customFont,// Applying custom font,
        )
    }
}
