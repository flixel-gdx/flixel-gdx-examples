package org.flixel.examples.blend;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import flash.display.BlendMode;
import flash.display.BlendModeGL20;

/**
 * A simply demo to test the various blending modes.
 * If GLES20 is not supported by the phone, then GLES10 will be used which doesn't have much blend modes.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	private String ImgBase = "examples/blend/base.png";
	private String ImgBlend = "examples/blend/blend.png";

	private ShaderProgram[] _shaders;
	private String[] _blends;
	private int _count;
	private FlxSprite _base;
	private FlxSprite _blend;
	
	
	@Override
	public void create()
	{			
		_base = new FlxSprite().loadGraphic(ImgBase);
		_blend = new FlxSprite().loadGraphic(ImgBlend);
		_base.blendTexture = _blend.getTexture();
		_count = 0;
		
		if(!Gdx.graphics.isGL20Available())
		{
			add(_blend);
			add(_base);
			_blends = new String[]{BlendMode.NORMAL, BlendMode.ALPHA, BlendMode.ADD, BlendMode.ERASE, BlendMode.MULTIPLY, BlendMode.SCREEN};
		}
		else
		{
			add(_base);
			_shaders = new ShaderProgram[]
			{
				BlendModeGL20.createProgram(BlendModeGL20.NORMAL),
				BlendModeGL20.createProgram(BlendModeGL20.LIGHTEN),
				BlendModeGL20.createProgram(BlendModeGL20.DARKEN),
				BlendModeGL20.createProgram(BlendModeGL20.MULTIPLY),
				BlendModeGL20.createProgram(BlendModeGL20.AVERAGE),
				BlendModeGL20.createProgram(BlendModeGL20.ADD),
				BlendModeGL20.createProgram(BlendModeGL20.SUBSTRACT),
				BlendModeGL20.createProgram(BlendModeGL20.DIFFERENCE),
				BlendModeGL20.createProgram(BlendModeGL20.NEGATION),
				BlendModeGL20.createProgram(BlendModeGL20.SCREEN),
				BlendModeGL20.createProgram(BlendModeGL20.EXCLUSION),
				BlendModeGL20.createProgram(BlendModeGL20.OVERLAY),
				BlendModeGL20.createProgram(BlendModeGL20.SOFT_LIGHT),
				BlendModeGL20.createProgram(BlendModeGL20.HARD_LIGHT),
				BlendModeGL20.createProgram(BlendModeGL20.COLOR_DODGE),
				BlendModeGL20.createProgram(BlendModeGL20.COLOR_BURN),
				BlendModeGL20.createProgram(BlendModeGL20.LINEAR_DODGE),
				BlendModeGL20.createProgram(BlendModeGL20.LINEAR_BURN),
				BlendModeGL20.createProgram(BlendModeGL20.LINEAR_LIGHT),	
				BlendModeGL20.createProgram(BlendModeGL20.VIVID_LIGHT),	
				BlendModeGL20.createProgram(BlendModeGL20.PIN_LIGHT),	
				BlendModeGL20.createProgram(BlendModeGL20.HARD_MIX),
				BlendModeGL20.createProgram(BlendModeGL20.REFLECT),
				BlendModeGL20.createProgram(BlendModeGL20.GLOW),
				BlendModeGL20.createProgram(BlendModeGL20.PHOENIX),
			};			
		}		
	}

	@Override
	public void update()
	{
		if(FlxG.mouse.justPressed())
		{
			if(!Gdx.graphics.isGL20Available())
			{
				if(_blends.length == ++_count)
					_count = 0;
				_base.blend = _blends[_count];				
			}
			else
			{
				if(_shaders.length == ++_count)
					_count = 0;
				_base.blendGL20 = _shaders[_count];				
			}
		}
		super.update();
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.destroyShaders();
	}
}
