package org.flixel.examples.blend;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.event.IFlxButton;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import flash.display.BlendMode;
import flash.display.BlendModeGL20;

/**
 * A simply demo to test the various blending modes. If GLES20 is not supported
 * by the phone, then GLES10 will be used which doesn't have much blend modes.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	private String ImgBase = "examples/blend/base.png";
	private String ImgBlend = "examples/blend/blend.png";

	private ShaderProgram[] _shaders;
	private String[] _blends;
	private int _blendCount;
	private int _shaderCount;
	private FlxSprite _base;
	private FlxSprite _blend;
	private boolean _useShader;
	private FlxButton _button;

	@Override
	public void create()
	{
		_base = new FlxSprite().loadGraphic(ImgBase);
		_blend = new FlxSprite().loadGraphic(ImgBlend);
		_base.blendTexture = _blend.getTexture();
		_blendCount = 0;
		_shaderCount = 0;
		_useShader = true;

		add(_blend);
		add(_base);
		_blends = new String[] { BlendMode.NORMAL, BlendMode.ALPHA,
				BlendMode.ADD, BlendMode.ERASE, BlendMode.MULTIPLY,
				BlendMode.SCREEN };

		_shaders = new ShaderProgram[] {
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
				BlendModeGL20.createProgram(BlendModeGL20.PHOENIX), };

		_button = new FlxButton(0, 0, " Shader ON", new IFlxButton()
		{
			@Override
			public void callback()
			{
				_useShader = !_useShader;
				_button.label.setText(_useShader ? "Shader OFF" : "Shader ON");
				_base.blend = null;
			}
		});
		add(_button);
	}

	@Override
	public void update()
	{
		if (FlxG.mouse.justPressed())
		{
			if (_useShader)
			{
				if (_shaders.length == ++_shaderCount)
					_shaderCount = 0;
				_base.blendGL20 = _shaders[_shaderCount];				
			}
			else
			{
				if (_blends.length == ++_blendCount)
					_blendCount = 0;
				_base.blend = _blends[_blendCount];
			}
		}
		super.update();
	}

	@Override
	public void destroy()
	{
		super.destroy();
		_button = null;
		_shaders = null;
		_blends = null;
		_base = null;
		_blend = null;
		FlxG.destroyShaders();
	}
}
