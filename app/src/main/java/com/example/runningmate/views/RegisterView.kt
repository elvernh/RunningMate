package com.example.runningmate.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.runningmate.R
import com.example.runningmate.enums.PagesEnum
import com.example.runningmate.repositories.FakeAuthenticationRepository
import com.example.runningmate.viewmodel.AuthenticationViewModel
import com.example.runningmate.views.components.AuthenticationOutlinedTextField
import com.example.runningmate.views.components.AuthenticationSubmitButton

@Composable
fun RegisterView(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavHostController
){
    val backgroundColor = Color(0xFF1E1E1E)
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.fillMaxSize().background(backgroundColor).padding(vertical = 78.dp, horizontal = 32.dp)) {
        Row(Modifier.fillMaxWidth().clickable(
            onClick = {
                navController.navigate(PagesEnum.Welcome.name){
                    popUpTo(PagesEnum.Register.name) { inclusive = true }
                }
            }
        ),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "", tint = Color.White)
            Text(
                text = "Back",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(Modifier.height(182.dp))
        Column{
            Row(Modifier.fillMaxWidth()){
                Text(
                    text = "Join Us Today",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 25.sp
                )
            }
            Spacer(Modifier.height(30.dp))
            Row(Modifier.fillMaxWidth()){
                AuthenticationOutlinedTextField(
                    inputValue = authenticationViewModel.usernameInput,
                    onInputValueChange = {
                        authenticationViewModel.changeUsernameInput(it)
                    },
                    inputLabel = stringResource(id = R.string.usernameText),
                    inputPlaceholder = stringResource(id = R.string.usernameText),
                    leadingIconSrc = painterResource(id = R.drawable.person),
                    keyboardType = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onKeyboardNext = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    Modifier.fillMaxWidth()
                )

            }
            Row(Modifier.fillMaxWidth()){
                AuthenticationOutlinedTextField(
                    inputValue = authenticationViewModel.emailInput,
                    onInputValueChange = {
                        authenticationViewModel.changeEmailInput(it)
                    },
                    inputLabel = stringResource(id = R.string.emailText),
                    inputPlaceholder = stringResource(id = R.string.emailText),
                    leadingIconSrc = painterResource(id = R.drawable.email),
                    keyboardType = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    onKeyboardNext = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    Modifier.fillMaxWidth()
                )
            }
            Row(Modifier.fillMaxWidth()){
                AuthenticationOutlinedTextField(
                    inputValue = authenticationViewModel.passwordInput,
                    onInputValueChange = {
                        authenticationViewModel.changePasswordInput(it)
                    },
                    inputLabel = stringResource(id = R.string.passwordText),
                    inputPlaceholder = stringResource(id = R.string.passwordText),
                    leadingIconSrc = painterResource(id = R.drawable.outline_lock_24),
                    keyboardType = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    onKeyboardNext = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    Modifier.fillMaxWidth()
                )
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)){
                AuthenticationSubmitButton(
                    buttonText = stringResource(id = R.string.submit),
                    onButtonClick = {
                        authenticationViewModel.registerUser(navController)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, apiLevel = 34)
@Composable
fun preRegisterView(){
    val mockViewModel = AuthenticationViewModel(FakeAuthenticationRepository())
    RegisterView(authenticationViewModel = mockViewModel, navController = rememberNavController())
}