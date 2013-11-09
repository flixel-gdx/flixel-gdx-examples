package org.flixel.examples.controller.mappings;

import org.flixel.system.input.GamepadMapping;

/**
 * Xbox 360 controller.
 * 
 * @author Ka Wing Chin
 */
public class Xbox360Controller extends GamepadMapping
{
	// Not used
	public int BUTTON_BACK = 6;
	public int AXIS_LEFT_TRIGGER = 4;
	public int AXIS_RIGHT_TRIGGER = 4;
	
	public Xbox360Controller()
	{
		super(new String[]
				{
					"Controller (Gamepad for Xbox 360)", 
					"Controller (XBOX 360 For Windows)", 
					"Controller (Xbox 360 Wireless Receiver for Windows)",
					"Controller (Xbox wireless receiver for windows)", 
					"XBOX 360 For Windows (Controller)", 
					"Xbox 360 Wireless Receiver",
					"Xbox Receiver for Windows (Wireless Controller)", 
					"Xbox wireless receiver for windows (Controller)"
				});
		
		BUTTON_B = 1;
		BUTTON_A = 0;
		BUTTON_Y = 3;
		BUTTON_X = 2;
		BUTTON_L1 = 4;
		BUTTON_R1 = 5;		
		BUTTON_L3 = 8;
		BUTTON_R3 = 9;
		BUTTON_START = 7;
		AXIS_LEFT_X = 0;
		AXIS_LEFT_Y = 1;
		AXIS_RIGHT_X = 3;
		AXIS_RIGHT_Y = 2;
	}
}
