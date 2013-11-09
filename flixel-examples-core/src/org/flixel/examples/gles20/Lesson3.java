package org.flixel.examples.gles20;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Lesson 3: Post-Processing Effects
 * https://github.com/mattdesl/lwjgl-basics/wiki/ShaderLesson3
 * 
 * @author Ka Wing Chin
 */
public class Lesson3 extends FlxState
{
	private final String VERTEX = "examples/gles20/lesson3/vertex_shader.glsl";	
	private final String FRAGMENT = "examples/gles20/lesson3/fragment_shader.glsl";
	private final String Img = "examples/gles20/lesson3/scene.png";
	
	@Override
	public void create()
	{		
		// important since we aren't using some uniforms and attributes that SpriteBatch expects
		ShaderProgram.pedantic = false;
		
		ShaderProgram shader = FlxG.loadShader("ppeffect", VERTEX, FRAGMENT);
								
		// Should actually be at override FlxGame.resize().
		shader.begin();
		shader.setUniformf("u_resolution", FlxG.width, FlxG.height);
		shader.end();
		
		FlxSprite sprite = new FlxSprite(0, 0, Img);
		sprite.x = FlxG.width - sprite.width;
		sprite.shader = shader;
		add(sprite);		
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.disposeShader("ppeffect");
	}
}

