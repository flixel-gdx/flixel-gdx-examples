package org.flixel.examples.userinterface;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxU;
import org.flixel.event.IFlxButton;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/**
 *
 * @author Ka Wing Chin
 */
public class Test extends FlxState
{
	public static final String FntRobotoRegular = "examples/userinterface/Roboto-Regular.ttf";
	private static Array<Class<?extends FlxState>> tests;
	public static int currentTest = 0;

	@Override
	public void create()
	{
		FlxG.setBgColor(0xFF111111);
		
		if(tests == null)
		{
			tests = new Array<Class<?extends FlxState>>();	
			tests.add(CheckBoxState.class);
			tests.add(RadioButtonState.class);
			tests.add(LabelState.class);
			tests.add(InputTextState.class);
			tests.add(TextAreaState.class);
			tests.add(SwitchState.class);
			tests.add(ButtonState.class);
			tests.add(TabState.class);
			tests.add(TabVerticalState.class);
			tests.add(SkinState.class);
		}
		
		// Mobile
		if(FlxG.mobile)
		{
			add(createButton(0, FlxG.height-30, "Previous", new IFlxButton(){@Override public void callback(){prev();}}));
			add(createButton(80, FlxG.height-30, "Next", new IFlxButton(){@Override public void callback(){next();}}));
		}
		else
		{
			add(new FlxText(0, FlxG.height-30, 300, "'Left/Right' arrows to go to previous/next example."));
		}
		
		String className = FlxU.getClassName(this, true);
		if(className.equals("Test") || className.equals("org.flixel.examples.userinterface.Test"))
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
	
	public FlxButton createButton(float x, float y, String label, IFlxButton callback)
	{
		FlxButton button = new FlxButton(x, y, label, callback);
		button.ignoreDrawDebug = true;
		button.scrollFactor.x = button.scrollFactor.y = 0;
		button.setSolid(false);
		button.moves = false;
		return button;
	}

	@Override
	public void update()
	{
		super.update();
		if(FlxG.keys.justPressed("RIGHT"))
			next();
		else if(FlxG.keys.justPressed("LEFT"))
			prev();
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
	
	private void prev()
	{
		if(0 > --currentTest)
			currentTest = tests.size-1;
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

