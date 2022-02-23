package com.yvkalume.eventcademy.ui.sharedcomponents

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colors.background,
            unfocusedIndicatorColor = MaterialTheme.colors.background,
            backgroundColor = MaterialTheme.colors.background.copy(alpha = 1f)
        )
    )
}