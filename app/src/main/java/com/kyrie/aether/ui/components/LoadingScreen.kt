package com.kyrie.aether.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kyrie.aether.ui.theme.LocalDimensions

@Composable
fun LoadingScreen() {
    val dimensions = LocalDimensions.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensions.spaceMedium)
        ) {
            CircularProgressIndicator()
            Text(
                text = "Loading weather data...",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}