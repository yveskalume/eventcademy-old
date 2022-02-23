package com.yvkalume.eventcademy.ui.sharedcomponents

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        modifier = modifier,
        placeholder = {
                      Text(text = "Recherche...")
        },
        onValueChange = onValueChange,
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray.copy(alpha = 0.5f),
            unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.5f),
            backgroundColor = Color.Gray.copy(alpha = 0.5f)
        )
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(value = "", onValueChange = {})
}