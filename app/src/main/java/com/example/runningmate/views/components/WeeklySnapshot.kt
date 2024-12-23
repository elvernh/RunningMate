package com.example.runningmate.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runningmate.R

@Composable
fun WeeklySnapshot(){
    val containerColor = Color(0xFF1E1E1E)
    val childColor = Color(0xFF8F8F8F)
    val customFont = FontFamily(Font(R.font.lexend)) // Custom font declaration
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerColor, shape = RoundedCornerShape(12 .dp))
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Your Weekly Snapshot",
                    fontFamily = customFont,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Steps Overview / day",
                    fontFamily = customFont,
                    color = childColor,
                    fontSize = 10.sp
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.directions_run),
                contentDescription = null,
                tint = Color(0xFFD9D9D9),
                modifier = Modifier.size(32.dp)
            )
        }
        Row {
            // Isi chart
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prevWeeklySnapshot(){
    WeeklySnapshot()
}