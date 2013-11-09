package org.flixel.examples.gles20;

import org.flixel.FlxG;
import org.flixel.FlxSprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * 
 * @author Ka Wing Chin
 */
public class Rock extends FlxSprite
{
	private final String ImgRock = "examples/gles20/lesson6/rock.png";
	private final String ImgRockNormals = "examples/gles20/lesson6/rockNormals.png";
	private final float DEFAULT_LIGHT_Z = 0.075f;
	private final Vector3 LIGHT_POS = new Vector3(0f, 0f, DEFAULT_LIGHT_Z);
	private Texture normals;

	public Rock(float x, float y)
	{
		loadGraphic(ImgRock);
		FlxSprite s = new FlxSprite().loadGraphic(ImgRockNormals);
		normals = s.getTexture();
	}

	@Override
	public void renderShader()
	{
		super.renderShader();
		// shader will now be in use...

		// update light position, normalized to screen resolution
		LIGHT_POS.x = (FlxG.mouse.x / (float) FlxG.width) * FlxG.camera._screenScaleFactorX;
		LIGHT_POS.y = 1f * FlxG.camera._screenScaleFactorY - ((FlxG.mouse.y / (float) FlxG.height) * FlxG.camera._screenScaleFactorY);
		// send a Vector4f to GLSL
		shader.setUniformf("LightPos", LIGHT_POS);
		
		// bind normal map to texture unit 1
		normals.bind(1);

		// bind diffuse color to texture unit 0
		// important that we specify 0 otherwise we'll still be bound to
		// glActiveTexture(GL_TEXTURE1)
		getTexture().bind(0);
	}

	@Override
	public void update()
	{
		// reset light Z
		if(FlxG.mouse.justPressed())
		{
			LIGHT_POS.z = DEFAULT_LIGHT_Z;
		}
		// handle mouse wheel
		if(FlxG.mouse.wheel != 0)
		{
			LIGHT_POS.z = Math.max(0f, LIGHT_POS.z - (FlxG.mouse.wheel * 0.005f));
		}
	}
}
