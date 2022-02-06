package com.yvkalume.eventcademy.ui.screen.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke? = null,
    backgroundColor: Color? = null,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    OutlinedButton(
        border = borderStroke,
        onClick = { onClick() },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = backgroundColor ?: MaterialTheme.colors.onPrimary
        ),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }
    )
}