package com.kyrie.aether.ui.components

import android.graphics.RuntimeShader
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.ui.Modifier
import com.kyrie.aether.ui.theme.LocalDimensions
import com.kyrie.aether.utility.extensions.applyShaderRuntimeEffect
import com.kyrie.aether.utility.shaders.ShaderComposeLayers

/***
 * A composable that implemented falling droplet shader effect layer.
 * Can be used in specific components (not entire scene background).
 ***/
@Composable
fun FallingDropletLayer(
    modifier: Modifier,
    fallingDropletShader: RuntimeShader,
    iTime: MutableFloatState,
) {
    val dimensions = LocalDimensions.current

    Card(
        modifier =
            modifier
                .applyShaderRuntimeEffect(
                    shader = fallingDropletShader,
                    iTime = { iTime.floatValue },
                    uniformName = ShaderComposeLayers.FALLING_DROPLET.value,
                ),
        shape = RoundedCornerShape(dimensions.radiusLarge),
    ) {}
}

/***
 * A composable that implemented fading droplet shader effect layer.
 * Can be used in specific components (not entire scene background).
 ***/
@Composable
fun FadingDropletLayer(
    modifier: Modifier,
    fadingDropletShader: RuntimeShader,
    iTime: MutableFloatState,
) {
    val dimensions = LocalDimensions.current

    Card(
        modifier =
            modifier
                .applyShaderRuntimeEffect(
                    shader = fadingDropletShader,
                    iTime = { iTime.floatValue },
                    uniformName = ShaderComposeLayers.FADING_DROPLET.value,
                ),
        shape = RoundedCornerShape(dimensions.radiusLarge),
    ) {}
}
