#ifdef GL_ES
	#define LOWP lowp
	precision mediump float;
#else
	#define LOWP
#endif


uniform sampler2D BaseImage;
uniform sampler2D BlendImage;
uniform float Opacity;

void main (void)
{
    vec4 base  = texture2D(BaseImage, gl_TexCoord[0].xy);
    vec4 blend = texture2D(BlendImage, gl_TexCoord[0].xy);

    vec4 result = (blend + base) * 0.5;
         result = clamp(result, 0.0, 1.0);

    gl_FragColor = mix(base, result, Opacity);
}