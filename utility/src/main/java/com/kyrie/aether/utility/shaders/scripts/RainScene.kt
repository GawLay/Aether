package com.kyrie.aether.utility.shaders.scripts

/**
 *
 * Description.
 * FALL_SPEED 0.3 How fast droplets fall (0.1=slow, 1.0=fast)
 * TIME_SPREAD 10.0 Spawn timing spread (higher=more staggered)
 * MIN_RADIUS_PX 5.0 Smallest droplet size in pixels
 * MAX_RADIUS_PX 13.0 Largest droplet size in pixels
 * MIN_OPACITY 0.4 Minimum transparency (0.0=invisible, 1.0=solid)
 * MAX_OPACITY 1.0 Maximum transparency
 * DROPLET_SOFTNESS 0.7 Edge blur (0.5=very soft, 1.0=sharp)
 * BASE_ALPHA 0.5 Overall visibility multiplier
 *
 * // Light drizzle
 * DROPLET_COUNT = 40
 * FALL_SPEED = 0.2
 * MIN_RADIUS_PX = 3.0
 * MAX_RADIUS_PX = 8.0
 *
 * // Heavy rain
 * DROPLET_COUNT = 120
 * FALL_SPEED = 0.5
 * MIN_RADIUS_PX = 6.0
 * MAX_RADIUS_PX = 15.0
 *
 * // Subtle background rain
 * BASE_ALPHA = 0.3
 * MIN_OPACITY = 0.2
 * MAX_OPACITY = 0.6
 * **/
object RainScene {
    const val RAIN_LAYER = """
            uniform shader weatherSceneShaderComposable;
            uniform float2 iResolution;
            uniform float iTime;
            
            half4 main(float2 fragCoord) {
                float2 uv = fragCoord / iResolution.xy;
                float3 color = float3(0.1, 0.15, 0.25); // base color
                
                const int DROPLET_COUNT = 80;           // Number of droplets (more = denser rain)
                const float FALL_SPEED = 0.3;           // Speed of falling (higher = faster)
                const float TIME_SPREAD = 10.0;         // How spread out droplet spawns are
                const float MIN_RADIUS_PX = 5.0;        // Minimum droplet size in pixels
                const float MAX_RADIUS_PX = 13.0;       // Maximum droplet size in pixels
                const float MIN_OPACITY = 0.4;          // Minimum opacity (0.0 to 1.0)
                const float MAX_OPACITY = 1.0;          // Maximum opacity (0.0 to 1.0)
                const float DROPLET_SOFTNESS = 0.7;     // Edge softness (lower = softer edges)
                const float BASE_ALPHA = 0.5;           // Overall droplet visibility
                
                for (int i = 0; i < DROPLET_COUNT; i++) {
                    float seed = float(i);
                    
                    // Random X position
                    float xPos = fract(sin(seed * 12.9898) * 43758.5453);
                    
                    // Time offset so they don't all start together
                    float timeOffset = fract(sin(seed * 78.233) * 43758.5453) * TIME_SPREAD;
                    float t = fract((iTime * FALL_SPEED) + timeOffset);
                    
                    // Fall from top to bottom
                    float yPos = t * 1.2 - 0.1; // -0.1 to 1.1
                    
                    // Random size
                    float sizeVariation = fract(sin(seed * 34.567) * 43758.5453);
                    float radiusPixels = sizeVariation * (MAX_RADIUS_PX - MIN_RADIUS_PX) + MIN_RADIUS_PX;
                    float radius = radiusPixels / iResolution.y;
                    
                    float2 pos = float2(xPos, yPos);
                    float2 diff = uv - pos;
                    float dist = length(diff);
                    
                    // Random opacity
                    float opacity = fract(sin(seed * 56.789) * 43758.5453) * (MAX_OPACITY - MIN_OPACITY) + MIN_OPACITY;
                    
                    // Simple circle droplet
                    float alpha = smoothstep(radius, radius * DROPLET_SOFTNESS, dist) * BASE_ALPHA * opacity;
                    
                    // Light blue tint
                    float3 dropColor = float3(0.85, 0.92, 0.98);
                    color = mix(color, dropColor, alpha);
                }
                
                return float4(color, 1.0);
            }
"""
}