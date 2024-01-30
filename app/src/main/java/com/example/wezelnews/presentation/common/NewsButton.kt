package com.example.wezelnews.presentation.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NewsButton(
    text:String,
    onClick:()->Unit
){

    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
containerColor = Color.Blue,
        contentColor = Color.White
    ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
    Text(text = text,
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))
    }

}
@Composable
fun NewsTextButton(
    text:String,
    onClick: () -> Unit
){
    val textColor = if (isSystemInDarkTheme()) {
        Color.White // Use white text color for dark theme
    } else {
        Color.Black // Use black text color for light theme
    }
TextButton(onClick = onClick) {
Text(text = text,
    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
    color = textColor)
    
}
}