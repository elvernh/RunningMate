package com.example.runningmate.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.runningmate.R

@Composable
fun AuthenticationOutlinedTextField(
    inputValue: String,
    onInputValueChange: (String) -> Unit,
    inputLabel: String,
    inputPlaceholder: String,
    leadingIconSrc: Painter,
    keyboardType: KeyboardOptions,
    onKeyboardNext: KeyboardActions,
    modifier: Modifier = Modifier
){
    val primaryColor = Color(0xFF9CFF00)
    OutlinedTextField(
        value = inputValue,
        onValueChange = {
            onInputValueChange
        },
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
        keyboardOptions = keyboardType,
        keyboardActions = onKeyboardNext,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    )
}