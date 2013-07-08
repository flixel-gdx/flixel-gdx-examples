package org.flixel.examples.userinterface;

import org.flixel.FlxG;
import org.flixel.FlxText;
import org.flixel.ui.FlxSwitch;
import org.flixel.ui.FlxUISkin;
import org.flixel.ui.event.IFlxUIListener;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Ka Wing Chin
 */
public class SwitchState extends Test
{
	private final String ImgSwitch = "examples/userinterface/holo_dark/switch.png";

	
	@Override
	public void create()
	{
		super.create();
		
		final Array<FlxText> texts = new Array<FlxText>();
		FlxText text;
		for(int i = 0; i < 6; i++)
		{
			text = new FlxText(10, 30 + i * 60, FlxG.width, "LIGHT")
			.setFormat(FntRobotoRegular, 18, 0x333333);
			add(text);
			texts.add(text);
		}
		
		// Setup skin
		FlxUISkin skin = new FlxUISkin();
		skin.NORMAL = 0;
		skin.PRESSED = -1;
		skin.HIGHLIGHT = -1;
		skin.DISABLED = -1;
		skin.ACTIVE_NORMAL = 1;
		skin.labelPosition = FlxUISkin.LABEL_LEFT;
		skin.labelOffset.x = -10;
		skin.setFormat(FntRobotoRegular, 18);
		skin.setImage(ImgSwitch, 107, 34);
		
		final FlxSwitch _switch;
		add(_switch = createSwitch(130, 398, skin, "Turn the lights"));
		_switch.onUp = new IFlxUIListener()
		{
			@Override
			public void callback()
			{
				if(_switch.getActivated())
				{
					for(int i = 0; i < texts.size; i++)
						texts.get(i).setColor(0xFFFFFF);
				}
				else
				{
					for(int i = 0; i < texts.size; i++)
						texts.get(i).setColor(0x333333);
				}
			}
		};
	}
}

