package org.flixel.examples.gles20;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.event.IFlxShaderProgram;
import org.flixel.gles20.FlxShaderProgram;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;

/**
 * Lesson 6: Normal Mapping
 * https://github.com/mattdesl/lwjgl-basics/wiki/ShaderLesson6
 * 
 * @author Ka Wing Chin
 */
public class Lesson6 extends FlxState
{
	private static final String VERTEX = "examples/gles20/lesson6/vertex_shader.glsl";
	private static final String FRAGMENT = "examples/gles20/lesson6/fragment_shader.glsl";

	// our constants...	
	public static final float AMBIENT_INTENSITY = 0.2f;
	public static final float LIGHT_INTENSITY = 1f;

	// Light RGB and intensity (alpha)
	public static final Vector3 LIGHT_COLOR = new Vector3(1f, 0.8f, 0.6f);

	// Ambient RGB and intensity (alpha)
	public static final Vector3 AMBIENT_COLOR = new Vector3(0.6f, 0.6f, 1f);

	// Attenuation coefficients for light falloff
	public static final Vector3 FALLOFF = new Vector3(.4f, 3f, 20f);

	private FlxShaderProgram shader;
	private Rock rock;

	@Override
	public void create()
	{		
		ShaderProgram.pedantic = false;
		
		// Add callback restore the uniforms when the context is lost.
		shader = FlxG.loadShader("lighting", VERTEX, FRAGMENT, new IFlxShaderProgram()
		{			
			@Override
			public void loadShaderSettings(ShaderProgram shader)
			{
				// setup default uniforms
				shader.begin();

				// our normal map
				shader.setUniformi("u_normals", 1); // GL_TEXTURE1

				// light/ambient colors
				// LibGDX doesn't have Vector4 class at the moment, so we pass them
				// individually...
				shader.setUniformf("LightColor", LIGHT_COLOR.x, LIGHT_COLOR.y, LIGHT_COLOR.z, LIGHT_INTENSITY);
				shader.setUniformf("AmbientColor", AMBIENT_COLOR.x, AMBIENT_COLOR.y, AMBIENT_COLOR.z, AMBIENT_INTENSITY);
				shader.setUniformf("Falloff", FALLOFF);
				
				// Should actually be at override FlxGame.resize().
				shader.setUniformf("Resolution", FlxG.width, FlxG.height);

				// LibGDX likes us to end the shader program
				shader.end();
			}
		});
		
		add(rock = new Rock(0, 0));
		rock.shader = shader;
	}

	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.disposeShader("lighting");
	}

}
