package com.kyrie.aether.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShaderLayers(
    modifier: Modifier = Modifier,
    layers: List<@Composable (Modifier) -> Unit>
) {
    Box(modifier = modifier) {
        layers.forEach { layer ->
            layer(Modifier.matchParentSize())
        }
    }
}