package org.flixel.examples.gesture;

import org.flixel.FlxGesture;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.event.IFlxGesture;

public class PlayState extends FlxState {
	
	private FlxText text;
	
	@Override
	public void create () {

		text = new FlxText(10, 10, 200, "NO GESTURE");
		text.setSize(20);
		add(text);
		
		FlxGesture gesture = new FlxGesture();
		gesture.start(new IFlxGesture() {
			
			@Override
			public void callback (int Gesture) {
				switch (Gesture) {
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
				default:
					break;
				}
				
			}
		});
	}

}
