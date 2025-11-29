package com.kyrie.aether.ui.shaders

object Droplets {
    const val FALLING_DROPLET_LAYER = """
            uniform shader fallingDropletShaderComposable;
            uniform float2 iResolution;
            uniform float iTime;
            
            // Function to simulate fall, pause, and drop, mapped from [0, 1] for a single cycle
            float fallPauseDrop(float t) {
                // t is the normalized time within the cycle [0.0, 1.0]
                
                const float FALL_START_END = 0.3; // Portion of cycle for the initial fast fall
                const float PAUSE_START = 0.4;    // Start of the pause
                const float PAUSE_END = 0.7;      // End of the pause
                
                // Total distance the drop will travel in one cycle (normalized)
                const float MAX_DISTANCE = 1.0; 
                
                float pos = 0.0;
                
                // 1. Initial Fall (Accelerated)
                if (t < FALL_START_END) {
                    float t_norm = t / FALL_START_END; 
                    pos = pow(t_norm, 2.0) * (MAX_DISTANCE * 0.4); 
                } 
                // 2. Slow Down / Pause
                else if (t >= FALL_START_END && t <= PAUSE_END) {
                    float fallStartPos = pow(FALL_START_END / FALL_START_END, 2.0) * (MAX_DISTANCE * 0.4);
                    float t_norm = (t - FALL_START_END) / (PAUSE_END - FALL_START_END); 
                    float pausePos = fallStartPos + smoothstep(0.0, 1.0, t_norm) * (MAX_DISTANCE * 0.05); 
                    pos = pausePos;
                }
                // 3. Final Drop (Resuming the fall)
                else {
                    float t_norm = (t - PAUSE_END) / (1.0 - PAUSE_END); 
                    float pauseEndPos = pow(FALL_START_END / FALL_START_END, 2.0) * (MAX_DISTANCE * 0.4) + (MAX_DISTANCE * 0.05);
                    float remainingDistance = MAX_DISTANCE - pauseEndPos;
                    pos = pauseEndPos + pow(t_norm, 1.5) * remainingDistance;
                }
            
                return pos;
            }
            
            
            half4 main(float2 fragCoord) {
                float2 uv = fragCoord / iResolution.xy;
            
                //  COUNT & DENSITY 
                const int MAX_DROPS = 40;
                
                // LIGHTING & COLOR 
                // The top color of the gradient (used for light source / highlight)
                float3 TOP_DROP_COLOR = float3(0.7, 0.8, 0.95);
                // The bottom color of the gradient (used for shadow / base)
                float3 BOTTOM_DROP_COLOR = float3(0.9, 0.95, 1.0);
                // How aggressive the vertical gradient is inside the droplet (higher = sharper highlight/shadow)
                const float GRADIENT_SCALE = 0.15; 
                
                // SPEED & TIMING 
                const float BASE_FALL_SPEED = 0.03;   // Lower = slower overall fall
                const float SPEED_VARIATION = 0.05;   // Higher = drops fall at very different speeds
                
                //  SIZE & SHAPE 
                const float BASE_RADIUS = 0.008;      // Base radius of the droplet (normalized 0.0 to 1.0)
                const float RADIUS_VARIATION = 0.008; // Max random variation in radius
                const float STRETCH_MULTIPLIER = 3.5; // Controls the vertical stretch/elongation (higher = longer drops)
                
                //  TRANSLUCENCY 
                const float ALPHA_MULTIPLIER = 0.15;  // Main opacity multiplier (lower = more transparent)
                
                
                float3 topColor = TOP_DROP_COLOR;
                float3 bottomColor = BOTTOM_DROP_COLOR;
            
                float totalAlpha = 0.0;
                float3 dropletTint = float3(0.0);
            
                for (int i = 0; i < MAX_DROPS; i++) {
                    float seed = float(i);
                    //add these two variables to control spawn area
                    //otherwise ignore these.
                    const float SPAWN_MARGIN = 0.05;
                    const float SPAWN_RANGE = 0.9;
                    //Eg (
                    //  float initialX = SPAWN_MARGIN + fract(sin(seed * 12.9898) * 43758.5453) * SPAWN_RANGE;
                    //)
                    
                    
                    float initialX =  fract(sin(seed * 12.9898) * 43758.5453) ;
                    float initialY = fract(sin(seed * 78.233) * 43758.5453);
                    float2 initialPos = float2(initialX, initialY);
            
                    
                    // Slower and more natural falling speed
                    float fallSpeed = BASE_FALL_SPEED + fract(sin(seed * 5.678) * 12345.6) * SPEED_VARIATION;
                    
                    // Time in the cycle normalized to [0, 1]
                    float dropCycleTime = fract(iTime * fallSpeed + initialPos.y); 
                    
                    // Use the custom function for the vertical position offset
                    float fallOffset = fallPauseDrop(dropCycleTime); 
                    
                    // Apply the offset (must be subtracted because a positive offset means moving *down* on the screen)
                    float2 pos = float2(initialPos.x, initialPos.y + fallOffset);
                    
                    // Smoother fade-in/out and ensure fading while falling
                    // Use dropCycleTime for lifetime to sync fade with the full cycle
                    const float FADE_IN_END = 0.2;
                    const float FADE_OUT_START = 0.8;
                    const float FADE_OUT_END = 1.0;
                    float lifetime = dropCycleTime;
                    float visible = smoothstep(0.0, FADE_IN_END, lifetime) * (1.0 - smoothstep(FADE_OUT_START, FADE_OUT_END, lifetime));
                    float fade = visible;
            
                    const float STRETCH_VARIATION = 0.2;
                    float shapeSeed = sin(seed * 47.123) * 100.0;
                    float stretchFactor = fract(shapeSeed * 1.23) * STRETCH_VARIATION;
                    float stretchX = 1.0 + stretchFactor * STRETCH_MULTIPLIER;
                    float stretchY = 0.7;
                    float2x2 rot = float2x2(1.0, 0.0, 0.0, 1.0);
            
                    float2 distortedUV = (uv - pos) * rot;
                    distortedUV.x *= stretchX;
                    distortedUV.y *= stretchY;
            
                    const float NOISE_STRENGTH = 0.1;
                    float noise = fract(sin(dot(distortedUV, float2(12.9898, 78.233))) * 43758.5453) * NOISE_STRENGTH;
                    float dist = length(distortedUV) * (1.0 + noise);
            
            
                    const float EDGE_SMOOTHNESS = 0.8;
                    float radiusSeed = sin(seed * 98.765) * 4321.0;
                    float radius = BASE_RADIUS + fract(radiusSeed) * RADIUS_VARIATION;
                    
                    float edge = smoothstep(radius * EDGE_SMOOTHNESS, radius, dist);
                    
                    float baseAlpha = (1.0 - edge) * fade * ALPHA_MULTIPLIER;
            
                    float2 dir = normalize(uv - pos);
                    const float GRADIENT_BASE = 0.5;// center the gradient
                    float vertical = clamp(-dir.y * GRADIENT_SCALE + GRADIENT_BASE, 0.0, 1.0);
                    float3 dropColor = mix(topColor, bottomColor, vertical);
                    
                    const float MIN_ALPHA_MULTIPLIER = 0.2;
                    float alphaMultiplier = max(MIN_ALPHA_MULTIPLIER, pow(vertical, 0.5));
                    float alpha = baseAlpha * alphaMultiplier;
                    
                    //blend
                    dropletTint = mix(dropletTint, dropColor, alpha);
                    totalAlpha = max(totalAlpha, alpha);
                }
                return half4(dropletTint, totalAlpha);
            }
            """

    const val FADING_DROPLET_LAYER = """
    uniform shader fadingDropletShaderComposable;  // compose view
    uniform float2 iResolution;
    uniform float iTime;
    
    half4 main(float2 fragCoord) {
        // Normalize pixel coordinates (0–1)
        float2 uv = fragCoord / iResolution.xy;
    
        // ==========================================================
        // Adjust these constants to change the look and animation.
        // ==========================================================
        
        // DENSITY & SIZE
        const int DROPLET_COUNT = 60;
        const float DROPLET_BASE_RADIUS = 0.01;      // Base size of all droplets (normalized)
        const float SHAPE_NOISE_STRENGTH = 0.1;      // Amount of noise/wobble applied to the droplet shape (higher = more irregular)
        const float MAX_STRETCH_RANGE = 0.8;         // Max random stretch applied to shape (e.g., 0.8 means 1.0 to 1.8 stretch)
        const float SHAPE_EDGE_SMOOTHNESS = 0.8;     // Controls how sharp or soft the droplet's edge is (1.0 = sharper)

        // TIMING & LIFETIME
        const float ANIMATION_SPEED = 0.2;            // Speed at which droplets cycle through their lifetime (lower = slower)
        const float RANDOM_TIME_OFFSET = 10.0;        // Spreads out the start time of the fade cycle for droplets
        const float LIFETIME_FADE_IN_END = 0.2;       // Normalized time (0-1) when fade-in finishes
        const float LIFETIME_FADE_OUT_START = 0.7;    // Normalized time (0-1) when fade-out begins
        const float LIFETIME_FADE_OUT_END = 0.8;      // Normalized time (0-1) when fade-out finishes

        // COLOR & TRANSLUCENCY
        // Highlight color (top of gradient)
        const float3 DROPLET_TOP_COLOR = float3(0.7, 0.8, 0.95);
        // Base color (bottom of gradient)
        const float3 DROPLET_BOTTOM_COLOR = float3(0.9, 0.95, 1.0);
        const float DROPLET_OPACITY = 0.15; // Main opacity multiplier (lower = more transparent)
        const float GRADIENT_INTENSITY = 0.15; // How aggressive the vertical light/shadow gradient is
        const float GRADIENT_CENTER = 0.5;     // Vertical center point for the gradient effect
        
        // ==========================================================
    
        float3 topColor = DROPLET_TOP_COLOR;
        float3 bottomColor = DROPLET_BOTTOM_COLOR;
    
        float totalAlpha = 0.0;
        float3 dropletTint = float3(0.0);
    
        for (int i = 0; i < DROPLET_COUNT; i++) {
            // Randomized droplet position (static)
            float2 pos = fract(sin(float(i) * float2(12.9898, 78.233)) * 43758.5453);
            
            // lifetime
            float t = fract(iTime * ANIMATION_SPEED + sin(float(i) * 12.9898) * RANDOM_TIME_OFFSET);
            float visible = smoothstep(0.1, LIFETIME_FADE_IN_END, t) * (1.0 - smoothstep(LIFETIME_FADE_OUT_START, LIFETIME_FADE_OUT_END, t));
            float fade = visible;
    
            // shape
            float seed = sin(float(i) * 47.123) * 100.0; //random shapes
            float stretchX = 1.0 + fract(seed * 1.23) * MAX_STRETCH_RANGE; // 1.0 to 1.8
            float stretchY = 1.0 + fract(seed * 2.34) * MAX_STRETCH_RANGE; // 1.0 to 1.8
            float angle = fract(seed * 3.45) * 6.283; // 0 to 2PI (purpose - for like realistic water drop) water drop don't have fix angle right?
            float2x2 rot = float2x2(cos(angle), -sin(angle),
                               sin(angle), cos(angle)); // rotation matrix
            
            // Apply rotation and stretching
            float2 distortedUV = (uv - pos) * rot;
            distortedUV.x *= stretchX;
            distortedUV.y *= stretchY;
    
            // Add some noise for organic water-like shapes
            float noise = fract(sin(dot(distortedUV, float2(12.9898, 78.233))) * 43758.5453) * SHAPE_NOISE_STRENGTH;
            float dist = length(distortedUV) * (1.0 + noise);
    
            
            float radius = DROPLET_BASE_RADIUS;
            float edge = smoothstep(radius * SHAPE_EDGE_SMOOTHNESS, radius, dist);
            
            float baseAlpha = (1.0 - edge) * fade * DROPLET_OPACITY;
            
            // Gradient effect
            float2 dir = normalize(uv - pos);
            float vertical = clamp(-dir.y * GRADIENT_INTENSITY + GRADIENT_CENTER, 0.0, 1.0);
            float3 dropColor = mix(topColor, bottomColor, vertical);
    
            // mix the color for better droplet visual...
            float alpha = baseAlpha * max(0.2, pow(vertical, 0.5));
            
            dropletTint = mix(dropletTint, dropColor, alpha);
            totalAlpha = max(totalAlpha, alpha);
        }
    
        // Return only the droplet colors with transparency
        return float4(dropletTint, totalAlpha);
    }
    """
}