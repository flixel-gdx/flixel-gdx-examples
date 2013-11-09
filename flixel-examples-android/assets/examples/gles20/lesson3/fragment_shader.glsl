#ifdef GL_ES
	#define LOWP lowp
	precision mediump float;
#else
	#define LOWP
#endif

// texture 0
uniform sampler2D u_texture;

// our screen resolution, set from Java whenever the display is resized
uniform vec2 u_resolution;

// "in" attributes from our vertex shader
varying vec4 v_color;
varying vec2 v_texCoord;

// the radius of our circle
const float RADIUS = 0.75;

// the softness of our circle edges, between 0.0 and 1.0
const float SOFTNESS = 0.45;

// sepia color, adjust to tate
const vec3 SEPIA = vec3(1.2, 1.0, 0.8);

void main() 
{
	// sample the texture
	vec4 texColor = texture2D(u_texture, v_texCoord);
	
	// determine origin
	vec2 position = (gl_FragCoord.xy / u_resolution.xy) - vec2(0.5);
	
	// OPTIONAL: correct for aspect ratio
	//position.x *= u_resolution.x / u_resolution.y;
	
	// determine the vector length of the center position
	float len = length(position);

	// 1. VIGNETTE, our vignette effect, using smoothstep
	float vignette = smoothstep(RADIUS, RADIUS - SOFTNESS, len);

	// apply our vignette with 50% opacity
	texColor.rgb = mix(texColor.rgb, texColor.rgb * vignette, 0.5);
	
	// 2. Grayscale, uses NTSC conversion weights
	float gray = dot(texColor.rgb, vec3(0.299, 0.587, 0.114));
	
	// 3. Sepia, create our sepia tone from some constant value
	vec3 sepiaColor = vec3(gray) * SEPIA;
	
	// again we'll use mix so that the sepia effect is at 75%
	texColor.rgb = mix(texColor.rgb, sepiaColor, 0.75);

	// final colors, multiplied by vertex color
	gl_FragColor = texColor * v_color;
}