package org.flixel.examples.controller;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxU;
import org.flixel.examples.controller.mappings.OuyaController;
import org.flixel.examples.controller.mappings.PS2Adaptor;
import org.flixel.examples.controller.mappings.PSPFuSa;
import org.flixel.examples.controller.mappings.Xbox360Controller;
import org.flixel.plugin.GamepadManager;
import org.flixel.system.input.Gamepad;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/**
 *
 * @author Ka Wing Chin
 */
public class Test extends FlxState
{
	private static Array<Class<?extends FlxState>> tests;
	public static int currentTest = 0;
	public Gamepad gamepad1;
	public Gamepad gamepad2;
	public Gamepad gamepad3;
	
	@Override
	public void create()
	{
		// Add the GamepadManager plugin.
		FlxG.addPlugin(new GamepadManager());
				
		// Add some mappings, ID is placed between brackets.
		// PSP (FuSa GamePad).
		GamepadManager.addMapping(new PSPFuSa());
		// Dual Shock 2 + Logic3 Adapter (PS(R) Gamepad Adaptor).
		GamepadManager.addMapping(new PS2Adaptor());
		// Ouya Controller (Ouya Controller)
		GamepadManager.addMapping(new OuyaController());
		// Xbox 360 Controller (ID varies).
		GamepadManager.addMapping(new Xbox360Controller());
		
		// Create gamepads. You can add as many as you like.
		GamepadManager.addGamepad(gamepad1 = new Gamepad()); // 1st controller
		GamepadManager.addGamepad(gamepad2 = new Gamepad()); // 2nd controller
		GamepadManager.addGamepad(gamepad3 = new Gamepad()); // 3rd controller
		
		add(new FlxText(20, 20, FlxG.width, "Press Start on Gamepad 1 for next example"));
		
		if(tests == null)
		{
			tests = new Array<Class<? extends FlxState>>();
			tests.add(PlayState.class);
			tests.add(PlayState2.class);
		}
		
		if(FlxU.getClassName(this, true).equals("Test"))
		{
			try
			{				
				FlxG.switchState(ClassReflection.newInstance(tests.get(currentTest)));
			}
			catch(Exception e)
			{
				FlxG.log(e.getMessage());
				return;
			}
		}
	}
	
	@Override
	public void update()
	{
		super.update();
		if(gamepad1.justReleased("BUTTON_START"))
		{
			next();
		}
	}
	
	private void next()
	{
		if(tests.size <= ++currentTest)
			currentTest = 0;
		try
		{				
			FlxG.switchState(ClassReflection.newInstance(tests.get(currentTest)));
		}
		catch(Exception e)
		{
			FlxG.log(e.getMessage());
			return;
		}
	}

	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.getPlugin(GamepadManager.class).destroy();
		FlxG.log(FlxG.removePluginType(GamepadManager.class));
		gamepad1 = null;
		gamepad2 = null;
		gamepad3 = null;
	}
}

