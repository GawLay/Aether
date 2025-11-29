package com.kyrie.aether.utility.extensions

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import com.kyrie.aether.utility.shaders.ShaderUniforms

fun Modifier.applyShaderRuntimeEffect(
    shader: RuntimeShader,
    iTime: () -> Float,
    uniformName: String
): Modifier {
    return this
        .onSizeChanged { size ->
            shader.setFloatUniform(
                ShaderUniforms.I_RESOLUTION.value,
                size.width.toFloat(),
                size.height.toFloat()
            )
        }
        .graphicsLayer {
            clip = true
            shader.setFloatUniform(ShaderUniforms.I_TIME.value, iTime())
            renderEffect = RenderEffect
                .createRuntimeShaderEffect(shader, uniformName)
                .asComposeRenderEffect()
        }
}