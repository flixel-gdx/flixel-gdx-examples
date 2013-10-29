#ifdef GL_ES
	#define LOWP lowp
	precision mediump float;
#else
	#define LOWP
#endif

//SpriteBatch will use texture unit 0
uniform sampler2D u_texture;

//"in" varyings from our vertex shader
varying vec4 v_color;
varying vec2 v_texCoord;

void main() 
{
	//sample the texture
	vec4 texColor = texture2D(u_texture, v_texCoord);
	
	//invert the red, green and blue channels
	texColor.rgb = 1.0 - texColor.rgb;
	
	//final color
	gl_FragColor = v_color * texColor;
}