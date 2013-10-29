package org.flixel.examples.gles20;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.examples.analog.PlayState;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Lesson 1: Red Boxes
 * https://github.com/mattdesl/lwjgl-basics/wiki/ShaderLesson1
 * 
 * @author Ka Wing Chin
 */
public class Lesson1 extends PlayState
{
	private final String VERT =
			"attribute vec4 "+ShaderProgram.POSITION_ATTRIBUTE+";\n" +
			"uniform mat4 u_projTrans;\n" +
			" \n" +
			"void main() {\n" +
			" gl_Position = u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
			"}";
	private final String FRAG =
			"void main() {\n" +
			" gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);\n" +
			"}";
	private ShaderProgram shader;
	
	@Override
	public void create()
	{
		// important since we aren't using some uniforms and attributes that SpriteBatch expects
		ShaderProgram.pedantic = false;
		
		shader = new ShaderProgram(VERT, FRAG);
		// Validate
		FlxG.isShaderCompiled(shader);		
		
		// The sprite batch will use the shader. 
		// All boxes will be red, unless ignoreBatchShader is set to true.
		FlxG.batch.setShader(FlxG.batchShader = shader);
		
		add(new FlxSprite(10, 10).makeGraphic(128, 128));		
		FlxSprite sprite;
		add(sprite = new FlxSprite(148, 10).makeGraphic(64, 64));
		sprite.ignoreBatchShader = true;
		add(new FlxSprite(220, 10).makeGraphic(32, 32));
		add(sprite = new FlxSprite(260, 10).makeGraphic(16, 16));
		sprite.ignoreBatchShader = true;
		add(new FlxSprite(284, 10).makeGraphic(8, 8));
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		shader.dispose();
		shader = null;
	}
}

