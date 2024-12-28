package com.example.runningmate.views

import android.view.ViewDebug.IntToString
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runningmate.R
import com.example.runningmate.repositories.AchievementRepository
import com.example.runningmate.services.AchievementAPIService
import com.example.runningmate.viewmodel.AchievementViewModel
import com.example.runningmate.views.components.AchievementCard
import com.example.runningmate.views.components.AchievementState

// Step 1: Data model for the cards
data class AchievementCardData(
    val title: String,
    val description: String,
    val imageRes: Int,
    val state: AchievementState
)

// AchievementView
@Composable
fun AchievementView(viewModel: AchievementViewModel) {
    val achievements by viewModel.achievements.collectAsState()
    val cards = listOf(
        AchievementCardData("First 1km", "Complete your first one kilometer.", R.drawable.image_1, AchievementState.UNLOCKED),
        AchievementCardData("Speed Run", "Run 100m within 15s.", R.drawable.image_2, AchievementState.UNLOCKED),
        AchievementCardData("Total Distance", "You have run a total of 100m.", R.drawable.image_3, AchievementState.LOCKED),
        AchievementCardData("7 day-Streak", "Daily jogging routine.", R.drawable.image_4, AchievementState.LOCKED),
        AchievementCardData("Social Star", "Add 10 Friends", R.drawable.image_5, AchievementState.LOCKED),
        AchievementCardData("Calories Crusher", "Burn a total of 1000Kcal", R.drawable.image_6, AchievementState.LOCKED),
        AchievementCardData("Marathoner", "Run a total of 42 km (26.2 miles).", R.drawable.image_7, AchievementState.LOCKED),
        AchievementCardData("First Timer", "Used the app for the first time", R.drawable.image_8, AchievementState.LOCKED)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF171717))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Achievement Icon",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )

            Spacer(modifier = Modifier.width(70.dp))

            Text(
                text = "Achievements",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(achievements) { rowIndex ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val cardData1 = cards[rowIndex * 2]
                    AchievementCard(
                        title = rowIndex.name,
                        description = rowIndex.description,
                        imageRes = rowIndex.image,
                        state = cardData1.state,
                        modifier = Modifier.weight(1f)
                    )

                    if (rowIndex * 2 + 1 < cards.size) {
                        val cardData2 = cards[rowIndex * 2 + 1]
                        AchievementCard(
                            title = cardData2.title,
                            description = cardData2.description,
                            imageRes = cardData2.imageRes,
                            state = cardData2.state,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAchievementScreen() {
    AchievementView(viewModel = AchievementViewModel(AchievementRepository()))
}
