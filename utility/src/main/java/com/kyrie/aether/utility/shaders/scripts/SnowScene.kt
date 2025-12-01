package com.kyrie.aether.utility.shaders.scripts

import android.graphics.RuntimeShader
import com.kyrie.aether.utility.shaders.ShaderUtil
import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.factories.CoreShaderFactory

object SnowScene {

    fun createSnowShaderForCondition(condition: WeatherCondition): RuntimeShader? {
        val config = CoreShaderFactory.getSnowConfig(condition)
            ?: return null

        val shaderCode = """
            uniform shader weatherSceneShaderComposable;
            uniform float2 iResolution;
            uniform float iTime;
            
            half4 main(float2 fragCoord) {
                float2 uv = fragCoord / iResolution.xy;
                float3 color = float3(0.1, 0.15, 0.25); // background tint

                const int FLAKE_COUNT = ${config.dropletCount};
                const float FALL_SPEED = ${config.fallSpeed}; 
                const float TIME_SPREAD = ${config.timeSpread};
                const float MIN_RADIUS_PX = ${config.minRadiusPx};
                const float MAX_RADIUS_PX = ${config.maxRadiusPx};
                const float MIN_OPACITY = ${config.minOpacity};
                const float MAX_OPACITY = ${config.maxOpacity};
                const float SOFTNESS = ${config.dropletSoftness};
                const float BASE_ALPHA = ${config.baseAlpha};
                const float DRIFT_AMOUNT=0.02; // (gentle 0.01) use .02,.03  multiplier for stronger horizontal drift
                float aspect = iResolution.x / iResolution.y;
                float2 uvCorrected = uv;
                uvCorrected.x *= aspect;
                
                const float SIZE_MULTIPLIER = 0.7; // Scale down snowflake sizes range , 1 to 0 , bigger to smaller, consider moving to config data class.

                for (int i = 0; i < FLAKE_COUNT; i++) {
                    float seed = float(i);

                    float xPos = fract(sin(seed * 12.9898) * 43758.5453);
                    float timeOffset = fract(sin(seed * 78.233) * 43758.5453) * TIME_SPREAD;

                    // Snow falls slower
                    float t = fract((iTime * FALL_SPEED) + timeOffset);
                    float yPos = t * 1.2 - 0.1;

                    //horizontal drift (snow effect)
                    xPos += sin(iTime * 0.3 + seed) * DRIFT_AMOUNT;

                    float sizeVariation = fract(sin(seed * 34.567) * 43758.5453);
                    float radiusPixels = sizeVariation * (MAX_RADIUS_PX - MIN_RADIUS_PX) + MIN_RADIUS_PX;
                    radiusPixels *= SIZE_MULTIPLIER;
                    //aspect ratio to maintain circles
                    float radius = radiusPixels / iResolution.y;
                    
                    // Correct position for aspect ratio
                    float2 pos = float2(xPos * aspect, yPos);
                    float2 diff = uvCorrected - pos;
                    
                    // Use true Euclidean distance for perfect circles
                    float dist = length(diff);

                    float opacity = fract(sin(seed * 56.789) * 43758.5453) 
                        * (MAX_OPACITY - MIN_OPACITY) + MIN_OPACITY;

                    // Softer, circular edges
                    float alpha = 1.0 - smoothstep(radius * SOFTNESS, radius, dist);
                    alpha *= BASE_ALPHA * opacity;

                    // White snow dot with slight variation
                    float brightness = 0.9 + opacity * 0.1;
                    float3 flakeColor = float3(brightness, brightness, brightness);
                    color = mix(color, flakeColor, alpha);
                }

                return float4(color, 1.0);
            }
        """.trimIndent()

        return ShaderUtil.createShader(shaderCode)
    }
}