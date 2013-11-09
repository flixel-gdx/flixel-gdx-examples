#ifdef GL_ES
	#define LOWP lowp
	precision mediump float;
#else
	#define LOWP
#endif

//SpriteBatch will use texture unit 0
uniform sampler2D u_texture;
uniform sampler2D u_texture1;
uniform sampler2D u_mask;
uniform float Opacity;

//"in" varyings from our vertex shader
varying vec4 v_color;
varying vec2 v_texCoord;

void main() 
{
	//sample the color from the first texture
	vec4 base = texture2D(u_texture, v_texCoord);
	
	// sample the color from the second texture
	vec4 blend = texture2D(u_texture1, v_texCoord);
			
	// interpolate the colors based on the mask
	gl_FragColor = min(base, blend);
}