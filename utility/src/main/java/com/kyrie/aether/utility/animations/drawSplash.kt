package com.kyrie.aether.utility.animations

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

fun DrawScope.drawSplash(
    splash: Splash,
    progress: Float,
) {
    val particleCount = 12
    val maxRadius = 45f

    // Particle splash effect
    for (i in 0 until particleCount) {
        val angle = (i.toFloat() / particleCount) * 2f * Math.PI.toFloat()
        val speed = 0.6f + (i % 3) * 0.4f
        val distance = progress * maxRadius * speed

        // Upward arc then gravity
        val gravity = progress * progress * 20f
        val lift = (1f - progress) * 18f

        val x = splash.x + kotlin.math.cos(angle) * distance
        val y = splash.y + kotlin.math.sin(angle) * distance * 0.4f - lift + gravity

        val size = (5f - progress * 4f).coerceAtLeast(1f)
        val alpha = ((1f - progress) * 0.9f).coerceAtLeast(0f)

        drawCircle(
            color = Color(0.85f, 0.92f, 0.98f, alpha),
            radius = size,
            center = Offset(x, y),
        )
    }

    // Center ripple rings
    for (ring in 0..2) {
        val ringDelay = ring * 0.15f
        val ringProgress = ((progress - ringDelay) / (1f - ringDelay)).coerceIn(0f, 1f)

        val rippleRadius = ringProgress * maxRadius * 1.3f
        val rippleAlpha = ((1f - ringProgress) * 0.3f).coerceAtLeast(0f)

        drawCircle(
            color = Color.White.copy(alpha = rippleAlpha),
            radius = rippleRadius,
            center = Offset(splash.x, splash.y),
            style = Stroke(width = 1.5f),
        )
    }
}

data class Splash(
    val x: Float,
    val y: Float,
    val startTime: Long,
)
