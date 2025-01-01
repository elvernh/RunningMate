package com.example.runningmate.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.R
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.repositories.FakeAuthenticationRepository
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.views.components.AuthenticationOutlinedTextField
import com.example.runningmate.views.components.AuthenticationSubmitButton
import com.example.runningmate.views.components.BackButton

@Composable
fun LoginView(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController
){
    val backgroundColor = Color(0xFF1E1E1E)
    val focusManager = LocalFocusManager.current
    val customFont = FontFamily(Font(R.font.lexend)) // Custom font declaration

    Column(modifier = Modifier.fillMaxSize().background(backgroundColor).padding(vertical = 78.dp, horizontal = 32.dp)) {

        // Back Button
        BackButton(navController)

        Spacer(Modifier.height(182.dp))

        // Content section
        Column {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Sign in",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 25.sp,
                    fontFamily = customFont // Applying custom font
                )
            }

            Spacer(Modifier.height(30.dp))

            // Email field
            Row(Modifier.fillMaxWidth()) {
                AuthenticationOutlinedTextField(
                    inputValue = authenticationViewModel.emailInput,
                    onInputValueChange = { authenticationViewModel.changeEmailInput(it) },
                    inputLabel = stringResource(id = R.string.emailText),
                    inputPlaceholder = stringResource(id = R.string.emailText),
                    leadingIconSrc = painterResource(id = R.drawable.email),
                    onKeyboardNext = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    Modifier.fillMaxWidth()
                )
            }

            // Password field
            Row(Modifier.fillMaxWidth()) {
                AuthenticationOutlinedTextField(
                    inputValue = authenticationViewModel.passwordInput,
                    onInputValueChange = { authenticationViewModel.changePasswordInput(it) },
                    inputLabel = stringResource(id = R.string.passwordText),
                    inputPlaceholder = stringResource(id = R.string.passwordText),
                    leadingIconSrc = painterResource(id = R.drawable.outline_lock_24),
                    onKeyboardNext = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    Modifier.fillMaxWidth()
                )
            }

            // Submit button
            Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
                AuthenticationSubmitButton(
                    buttonText = stringResource(id = R.string.submit),
                    onButtonClick = {
                        authenticationViewModel.loginUser(navController)
                    },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Add a divider at the start
                HorizontalDivider(
                    color = Color.White,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f).padding(end = 5.dp)
                )

                // Add the text in the center
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(Modifier.height(15.dp))
                    Text(
                        text = stringResource(id = R.string.don_t_have_an_account_yet_text),
                        color = Color.White,
                        fontFamily = customFont,
                        fontSize = 10.sp,
                    )

                    Text(
                        text = stringResource(id = R.string.sign_up_text),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = customFont,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            navController.navigate(PagesEnum.Register.name) {
                                popUpTo(PagesEnum.Home.name) { inclusive = false }
                            }
                        }
                    )
                }

                // Add a divider at the end
                HorizontalDivider(
                    color = Color.White,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f).padding(start = 5.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, apiLevel = 34)
@Composable
fun prevLoginView(){
    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
    LoginView(authenticationViewModel = mockViewModel, navController = rememberNavController())
}
