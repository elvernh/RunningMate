package com.example.runningmate.views.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.runningmate.R

@Composable
fun AddFriendButton() {
    var isUsed by remember { mutableStateOf(false) }
    val customFont = FontFamily(Font(R.font.lexend))
    val grayColor = Color(0xFF8F8F8F)
    val neon = Color(0xFF9CFF00)

    Button(
        onClick = {
            // Toggle the isUsed state
            isUsed = !isUsed
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isUsed) grayColor else neon,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(16.dp)
            .height(48.dp)
            .width(200.dp)
    ) {
        Text(
            text = if (isUsed) "Requested" else "Add As Friend",
            color = if (isUsed) Color.White else Color.Black,
            fontFamily = customFont
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddFriend() {
    AddFriendButton()
}
