package org.flixel.examples.gles20;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Lesson 4: Multiple Texture Units
 * 
 * @author Ka Wing Chin
 */
public class Lesson4b extends FlxState
{
	private final String VERTEX = "examples/gles20/lesson4/vertex_shader.glsl";
	private final String FRAGMENT = "examples/gles20/lesson4/fragment_shader_b.glsl";
	private final String ImgGrass = "examples/gles20/lesson4/grass.png";
	private final String ImgDirt = "examples/gles20/lesson4/dirt.png";
	private final String ImgMask = "examples/gles20/lesson4/mask.png";

	private ShaderProgram shader;
	private FlxSprite grass;
	private FlxSprite dirt;
	private FlxSprite mask;
	private float time;

	@Override
	public void create()
	{
		add(grass = new FlxSprite(0, 0, ImgGrass));
		dirt = new FlxSprite(0, 0, ImgDirt);
		mask = new FlxSprite(0, 0, ImgMask);

		// important since we aren't using some uniforms and attributes that SpriteBatch expects
		ShaderProgram.pedantic = false;

		shader = FlxG.loadShader("multitexturing", VERTEX, FRAGMENT);

		shader.begin();
		shader.setUniformi("u_texture1", 1);
		shader.setUniformi("u_mask", 2);
		shader.end();

		// bind mask to glActiveTexture(GL_TEXTURE2)
		mask.getTexture().bind(2);
		// bind dirt to glActiveTexture(GL_TEXTURE1)
		dirt.getTexture().bind(1);
		// now we need to reset glActiveTexture to zero!!!! since sprite batch does not do this for us
		Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
		
		// tex0 will be bound when grass is drawn
		grass.shader = shader;
	}

	@Override
	public void draw()
	{
		shader.setUniformf("u_time", time += FlxG.elapsed);
		super.draw();
	}

	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.disposeShader("multitexturing");
		shader = null;
	}
}