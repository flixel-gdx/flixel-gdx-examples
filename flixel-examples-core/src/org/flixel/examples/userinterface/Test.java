package org.flixel.examples.userinterface;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxU;
import org.flixel.event.IFlxButton;
import org.flixel.ui.FlxCheckBox;
import org.flixel.ui.FlxDialogBox;
import org.flixel.ui.FlxInputText;
import org.flixel.ui.FlxNinePatchButton;
import org.flixel.ui.FlxRadioButton;
import org.flixel.ui.FlxRadioButtonGroup;
import org.flixel.ui.FlxSwitch;
import org.flixel.ui.FlxTab;
import org.flixel.ui.FlxUISkin;
import org.flixel.ui.event.IFlxUIListener;

import com.badlogic.gdx.utils.Array;

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
			tests.add(InputTextState.class);
			tests.add(SwitchState.class);
			tests.add(ButtonState.class);
			tests.add(TabState.class);
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
		
		if(FlxU.getClassName(this, true).equals("Test"))
		{
			try
			{				
				FlxG.switchState(tests.get(currentTest).newInstance());
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
	
	
	public FlxCheckBox createCheckBox(String ID, FlxUISkin skin, String label)
	{
		return new FlxCheckBox(0, 0, ID, skin, label);
	}
	
	public FlxRadioButton createRadioButton(String ID, FlxUISkin skin, String label, FlxRadioButtonGroup radioGroup)
	{		
		return new FlxRadioButton(0, 0, ID, skin, radioGroup, label);
	}
	
	public FlxInputText createInputText(float x, float y, FlxUISkin skin, String label)
	{
		FlxInputText inputText = new FlxInputText(x, y, skin, label);
		inputText.textField.setFormat(FntRobotoRegular, 18);
		inputText.setMaxLength(26);
		return inputText;
	}
	
	public FlxDialogBox createDialogBox(float x, float y, FlxUISkin skin, String label)
	{
		FlxDialogBox dialog = new FlxDialogBox(10, 300, skin, 300, label, "Enter your message");
		dialog.textField.setFormat(FntRobotoRegular, 18);
		dialog.setMaxLength(26);
		return dialog;
	}
	
	public FlxSwitch createSwitch(float x, float y, FlxUISkin skin, String label)
	{
		FlxSwitch _switch = new FlxSwitch(130, 398, skin, label);
		return _switch;
	}
	
	public FlxNinePatchButton createNinePatchButton(float x, float y, FlxUISkin skin, String label, IFlxUIListener onClick, int width, int height)
	{
		FlxNinePatchButton button = new FlxNinePatchButton(x, y, skin, label, onClick, width, height);
		button.stretch();
		return button;
	}
	
	public FlxTab createTab(FlxUISkin skin, String label)
	{
		FlxTab tab = new FlxTab(skin, label);
		return tab;
	}

	@Override
	public void update()
	{
		super.update();
		FlxG.collide();
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
			FlxG.switchState(tests.get(currentTest).newInstance());
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
			FlxG.switchState(tests.get(currentTest).newInstance());
		}
		catch(Exception e)
		{
			FlxG.log(e.getMessage());
			return;
		}
	}
}

