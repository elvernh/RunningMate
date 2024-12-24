package com.example.runningmate.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runningmate.R

@Composable
fun MenuBar(
    selectedMenu: String, // Add this parameter to track the selected menu
    onMenuClick: (String) -> Unit, // Callback for handling menu item clicks
    modifier: Modifier = Modifier
) {
    val ColorBasic = Color(0xFF1E1E1E)
    val customFont = FontFamily(Font(R.font.lexend)) // Custom font declaration
    val selectedColor = Color(0xFF9CFF00) // Green color for selected menu
    val defaultColor = Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(ColorBasic)
                .padding(start = 35.dp,end = 35.dp, top = 20.dp, bottom = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Menu Items
            MenuItem(
                icon = R.drawable.home,
                label = "Home",
                isSelected = selectedMenu == "Home",
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                customFont = customFont,
                onClick = { onMenuClick("Home") }
            )

            MenuItem(
                icon = R.drawable.record,
                label = "Record",
                isSelected = selectedMenu == "Record",
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                customFont = customFont,
                onClick = { onMenuClick("Record") }
            )

            MenuItem(
                icon = R.drawable.users,
                label = "Friends",
                isSelected = selectedMenu == "Friends",
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                customFont = customFont,
                onClick = { onMenuClick("Friends") }
            )

            MenuItem(
                icon = R.drawable.trello,
                label = "Profile",
                isSelected = selectedMenu == "Profile",
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                customFont = customFont,
                onClick = { onMenuClick("Profile") }
            )
        }

        // Adding the top border to the Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color(0xFFAAAAAA))
                .align(Alignment.TopCenter)
                .padding(horizontal = 35.dp)
        )
    }
}

@Composable
fun MenuItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    customFont: FontFamily,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            tint = if (isSelected) selectedColor else defaultColor,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        Text(
            text = label,
            color = if (isSelected) selectedColor else defaultColor,
            fontFamily = customFont,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun showMenuBar() {
    MenuBar(
        selectedMenu = "Home", // Set the initial selected menu
        onMenuClick = { menu -> println("Clicked on $menu") }
    )
}
