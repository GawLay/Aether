package com.kyrie.aether.utility.shaders.scripts

import android.graphics.RuntimeShader
import com.kyrie.aether.utility.shaders.ShaderUtil
import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.factories.CoreShaderFactory

object StarScene {
    fun createStarShaderForCondition(condition: WeatherCondition): RuntimeShader? {
        val config = CoreShaderFactory.getStarConfig(condition) ?: return null

        val shaderCode =
            """
            uniform shader weatherSceneShaderComposable;
            uniform float2 iResolution;
            uniform float iTime;
            
            half4 main(float2 fragCoord) {
                float2 uv = fragCoord / iResolution.xy;
                
                // Dark blue night sky
                float3 skyColor = float3(0.02, 0.03, 0.08);
                
                // Star parameters from config
                const int STAR_COUNT = ${config.dropletCount};
                const float MIN_BRIGHTNESS = ${config.minOpacity};
                const float MAX_BRIGHTNESS = ${config.maxOpacity};
                const float MIN_RADIUS_PX = ${config.minRadiusPx};
                const float MAX_RADIUS_PX = ${config.maxRadiusPx};
                const float STAR_SOFTNESS = ${config.dropletSoftness};
                const float BASE_ALPHA = ${config.baseAlpha};
                const float UPPER_LIMIT = ${config.upperLimit}; 
                
                // Twinkling parameters - ALL CONFIGURABLE
                const float TWINKLE_SPEED = ${config.twinkleSpeed};
                const float TIME_SPREAD = ${config.timeSpread};
                const float TWINKLE_AMOUNT = ${config.twinkleAmount};
                const float MIN_TWINKLE_SPEED = ${config.minTwinkleSpeed};
                const float MAX_TWINKLE_SPEED = ${config.maxTwinkleSpeed};
                const float MIN_VISIBLE_RATIO = ${config.minVisibleRatio};
                
                // Only show stars in upper half
                if (uv.y > UPPER_LIMIT) {
                    return float4(skyColor, 1.0);
                }
                
                // Aspect ratiofor perfect circles
                float aspect = iResolution.x / iResolution.y;
                float2 uvCorrected = uv;
                uvCorrected.x *= aspect;
                
                float3 finalColor = skyColor;
                
                for (int i = 0; i < STAR_COUNT; i++) {
                    float seed = float(i);
                    
                    // Generate random star position (upper half only)
                    float2 starPos = float2(
                        fract(sin(seed * 12.9898) * 43758.5453),
                        fract(sin(seed * 78.233) * 43758.5453) * UPPER_LIMIT
                    );
                    
                    // Apply aspect ratio to position
                    starPos.x *= aspect;
                    
                    // Calculate distance to star
                    float2 diff = uvCorrected - starPos;
                    float dist = length(diff);
                    
                    // Random star size
                    float sizeRand = fract(sin(seed * 45.678) * 43758.5453);
                    float radiusPixels = mix(MIN_RADIUS_PX, MAX_RADIUS_PX, sizeRand);
                    float starRadius = radiusPixels / iResolution.y;
                    
                    // Random base brightness for each star
                    float baseBrightness = mix(MIN_BRIGHTNESS, MAX_BRIGHTNESS, 
                                              fract(sin(seed * 34.567) * 43758.5453));
                    
                    // INDEPENDENT TWINKLE FOR EACH STAR
                    float timeOffset = fract(sin(seed * 56.789) * 43758.5453) * TIME_SPREAD;
                    
                    // Each star has different twinkle speed (configurable range)
                    float speedVariation = fract(sin(seed * 67.890) * 43758.5453);
                    float individualSpeed = mix(MIN_TWINKLE_SPEED, MAX_TWINKLE_SPEED, speedVariation);
                    
                    // Create independent twinkle wave for this star
                    float starTime = iTime * TWINKLE_SPEED * individualSpeed + timeOffset;
                    
                    // Use sin wave for smooth fade in/out
                    float twinkleWave = (sin(starTime) + 1.0) * 0.5;
                    
                    // Smooth the twinkle wave
                    twinkleWave = smoothstep(0.0, 1.0, twinkleWave);
                    
                    // Apply configurable twinkle to brightness
                    float minVisible = MIN_BRIGHTNESS * MIN_VISIBLE_RATIO;
                    float currentBrightness = mix(minVisible, baseBrightness, twinkleWave);
                    
                    // Create star shape with soft glow
                    float falloff = 1.0 - smoothstep(starRadius * STAR_SOFTNESS, 
                                                    starRadius, dist);
                    
                    // Bright white star color
                    float3 starColor = float3(1.0, 1.0, 1.0);
                    
                    // Star alpha
                    float starAlpha = falloff * currentBrightness * BASE_ALPHA;
                    
                    // Blend star with background
                    if (starAlpha > 0.001) {
                        finalColor = mix(finalColor, starColor, starAlpha);
                    }
                }
                
                return float4(finalColor, 1.0);
            }
            """.trimIndent()

        return ShaderUtil.createShader(shaderCode)
    }
}
