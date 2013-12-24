package org.flixel.examples.gesture;

import org.flixel.FlxG;
import org.flixel.FlxGesture;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.event.IFlxGesture;
import org.flixel.plugin.GestureManager;
import org.flixel.plugin.GestureManager.GestureData;

/**
 * A simple demo to demonstrate how gesture works.
 */
public class PlayState extends FlxState
{
	private FlxText text;

	@Override
	public void create()
	{
		text = new FlxText(10, 10, 200, "NO GESTURE");
		text.setSize(20);
		add(text);

		FlxG.addPlugin(new GestureManager());

		FlxGesture gesture = new FlxGesture();
		gesture.start(new IFlxGesture()
		{
			@Override
			public void callback(int Gesture, GestureData data)
			{
				switch(Gesture)
				{
					case FlxGesture.PAN:
						text.setText("PAN");
						break;
					case FlxGesture.DIRECTION_DOWN:
						text.setText("DOWN");
						break;
					case FlxGesture.DIRECTION_UP:
						text.setText("UP");
						break;
					case FlxGesture.DIRECTION_RIGHT:
						text.setText("RIGHT");
						break;
					case FlxGesture.DIRECTION_LEFT:
						text.setText("LEFT");
						break;
					case FlxGesture.DOUBLE_TAP:
						text.setText("DOUBLE TAP");
						break;
					case FlxGesture.LONG_PRESS:
						text.setText("LONG PRESS");
						break;
					case FlxGesture.TAP:
						text.setText("TAP");
						break;
					case FlxGesture.ZOOM:
						text.setText("ZOOM");
						break;
					case FlxGesture.PINCH:
						text.setText("PINCH");
						break;
					case FlxGesture.TOUCH_DOWN:
						text.setText("TOUCH DOWN");
						break;
					default:
						break;
				}
			}
		});
	}

}
