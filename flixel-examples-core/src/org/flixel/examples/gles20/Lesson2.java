package org.flixel.examples.gles20;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Lesson 2: Texture Sampling
 * https://github.com/mattdesl/lwjgl-basics/wiki/ShaderLesson2
 * 
 * @author Ka Wing Chin
 */
public class Lesson2 extends FlxState
{
	private final String VERTEX = "examples/gles20/lesson2/vertex_shader.glsl";	
	private final String FRAGMENT = "examples/gles20/lesson2/fragment_shader.glsl";
	private final String ImgGrass = "examples/gles20/lesson2/grass.png";
	
	private FlxSprite grass;
	private FlxSprite grass2;
	private FlxSprite grass3;


	@Override
	public void create()
	{
		// important since we aren't using some uniforms and attributes that SpriteBatch expects
		ShaderProgram.pedantic = false;
		
		ShaderProgram shader = FlxG.loadShader("sampling", VERTEX, FRAGMENT);
		
		// Validate
		FlxG.isShaderCompiled(shader);
		grass = new FlxSprite(0, 0, ImgGrass);
		add(grass);
		
		grass2 = new FlxSprite(128, 0, ImgGrass);
		grass2.shader = shader;
		add(grass2);
		
		grass3 = new FlxSprite(128, 128, ImgGrass);
		grass3.shader = shader;
		add(grass3);
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.disposeShader("sampling");
	}
}

