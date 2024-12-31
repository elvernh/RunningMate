package com.example.runningmate.views

//import android.view.ViewDebug.IntToString
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
import androidx.navigation.NavHostController
import com.example.runningmate.R
import com.example.runningmate.repositories.AchievementRepository
import com.example.runningmate.uiStates.AchievementDataStatusUIState
//import com.example.runningmate.services.AchievementAPIService
import com.example.runningmate.viewmodel.AchievementViewModel
import com.example.runningmate.views.components.AchievementCard
//import com.example.runningmate.views.components.AchievementState

data class AchievementCardData(
    val title: String,
    val description: String,
    val imageRes: Int,
)

@Composable
fun AchievementView(viewModel: AchievementViewModel,navController: NavHostController) {
    val achievementDataStatus by viewModel.achievementDataStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF171717))
            .padding(16.dp)
    ) {
        Text(
            text = "Achievements",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        when (achievementDataStatus) {
            is AchievementDataStatusUIState.Loading -> {
                Text(
                    text = "Loading achievements...",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is AchievementDataStatusUIState.Success -> {
                val achievements = (achievementDataStatus as AchievementDataStatusUIState.Success).data
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(achievements)


                     { achievement ->
                        AchievementCard(
                            title = achievement.name,
                            description = achievement.description,
                            imageUrl = achievement.image,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            is AchievementDataStatusUIState.Failed -> {
                val errorMessage = (achievementDataStatus as AchievementDataStatusUIState.Failed).errorMessage
                Text(
                    text = "Failed to load achievements: $errorMessage",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewAchievementScreen() {
    AchievementView(viewModel = AchievementViewModel(AchievementRepository()))
}
