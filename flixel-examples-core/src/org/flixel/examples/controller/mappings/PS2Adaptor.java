package org.flixel.examples.controller.mappings;

import org.flixel.system.input.GamepadMapping;

/**
 * Dual Shock 2 with Logic3 Adaptor.
 * 
 * @author Ka Wing Chin
 */
public class PS2Adaptor extends GamepadMapping
{
	public PS2Adaptor()
	{
		super("PS(R) Gamepad Adaptor");
		BUTTON_DPAD_UP = 12;
		BUTTON_DPAD_RIGHT = 13;
		BUTTON_DPAD_DOWN = 14;
		BUTTON_DPAD_LEFT = 15;
		BUTTON_B = 2; // CROSS
		BUTTON_A = 1; // CIRCLE
		BUTTON_Y = 3; // SQUARE
		BUTTON_X = 0; // TRIANGLE
		BUTTON_L1 = 4;
		BUTTON_R1 = 5;
		BUTTON_L2 = 6;
		BUTTON_R2 = 7;
		BUTTON_L3 = 10;
		BUTTON_R3 = 11;
		BUTTON_SELECT = 8;
		BUTTON_START = 9;
		BUTTON_MODE = 8;
		
		// Analog got swapped...
		AXIS_LEFT_X = 1;
		AXIS_LEFT_Y = 0;
		AXIS_RIGHT_X = 3;
		AXIS_RIGHT_Y = 2;
	}
}