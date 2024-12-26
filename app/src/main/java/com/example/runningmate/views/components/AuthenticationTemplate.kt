package com.example.runningmate.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthenticationOutlinedTextField(
    inputValue: String,
    onInputValueChange: (String) -> Unit,
    inputLabel: String,
    inputPlaceholder: String,
    leadingIconSrc: Painter,
    onKeyboardNext: KeyboardActions,
    modifier: Modifier = Modifier
){
    val primaryColor = Color(0xFF9CFF00)
    OutlinedTextField(
        value = inputValue,
        onValueChange = onInputValueChange
        ,
        singleLine = true,
        label = {
            Text(
                text = inputLabel,
                fontWeight = FontWeight.Medium
            )
        },
        placeholder = {
            Text(
                text = inputPlaceholder,
                fontWeight = FontWeight.Medium
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = primaryColor,
            focusedLabelColor = primaryColor,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        leadingIcon = {
            Image(
                painter = leadingIconSrc,
                contentDescription = null
            )
        },
//        keyboardOptions = keyboardType,
        keyboardActions = onKeyboardNext,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun AuthenticationSubmitButton(
    buttonText: String,
    onButtonClick: () -> Unit,
    textModifier: Modifier = Modifier,
){
    val primaryColor = Color(0xFF9CFF00)

    Button(
        onClick = onButtonClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(primaryColor),
    ) {
        Text(
            text = buttonText,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = textModifier,
            color = Color(0xFF1E1E1E)
        )
    }
}