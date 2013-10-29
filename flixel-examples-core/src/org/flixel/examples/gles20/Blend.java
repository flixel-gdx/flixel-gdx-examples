package org.flixel.examples.gles20;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.gles20.FlxShaderProgram;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import flash.display.BlendModeGL20;

/**
 * Blend test
 * @author Ka Wing Chin
 */
public class Blend extends FlxState
{	
	private final String ImgGrass = "examples/gles20/blend/grass.png";
	private final String ImgDirt = "examples/gles20/blend/dirt.png";
	private final String ImgBaseImage = "examples/gles20/blend/baseimage.jpg";
	private final String ImgBlendImage = "examples/gles20/blend/blendimage.jpg";
	
	private FlxSprite grass;
	private FlxSprite base;

	@Override
	public void create()
	{
		FlxG.setBgColor(0xFFFFFFFF);
		
		add(base = new FlxSprite(356, 0, ImgBaseImage));
		base.ID = 1;
		FlxSprite blend = new FlxSprite(356-108, 0, ImgBlendImage);
		
		add(grass = new FlxSprite(0, 0, ImgGrass));
		grass.ID = 2;
		FlxSprite dirt = new FlxSprite(0, 0, ImgDirt);
		
		ShaderProgram.pedantic = false;
		
//		BlendModeGL20.blend(BlendModeGL20.PHOENIX, base, blend);
//		BlendModeGL20.blend(BlendModeGL20.COLOR_DODGE, grass, dirt);
		
		
		FlxShaderProgram shader = BlendModeGL20.addBlendMode("test", BlendModeGL20.VERTEX, BlendModeGL20.ADD);
		FlxG.batch.setShader(FlxG.batchShader = shader);
		base.blendGL20 = shader;
		base.blendTexture = blend.getTexture();
		grass.blendGL20 = shader;
		grass.blendTexture = dirt.getTexture();
		
//		BlendModeGL20.blend(shader, grass, dirt);
//		base.ignoreBatchShader = true;
//		BlendModeGL20.blend(shader, base, blend);
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.destroyShaders();
	}
}

