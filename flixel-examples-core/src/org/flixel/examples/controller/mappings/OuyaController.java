package org.flixel.examples.controller.mappings;

import org.flixel.system.input.GamepadMapping;

/**
 *
 * @author Ka Wing Chin
 */
public class OuyaController extends GamepadMapping
{
	public OuyaController()
	{
		super("OUYA Game Controller");
		BUTTON_DPAD_UP = 19;
		BUTTON_DPAD_RIGHT = 22;
		BUTTON_DPAD_DOWN = 20;
		BUTTON_DPAD_LEFT = 21;
		BUTTON_B = 97;
		BUTTON_A = 96;
		BUTTON_Y = 100;
		BUTTON_X = 99;		
		BUTTON_L1 = 104;
		BUTTON_R1 = 105;
		BUTTON_L2 = 102;
		BUTTON_R2 = 107;
		BUTTON_L3 = 10;
		BUTTON_R3 = 11;
		BUTTON_MODE = 82;
		AXIS_LEFT_X = 0;
		AXIS_LEFT_Y = 1;
		AXIS_RIGHT_X = 3;
		AXIS_RIGHT_Y = 4;
	}
}

