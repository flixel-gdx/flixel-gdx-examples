package org.flixel.examples.controller.mappings;

import org.flixel.system.input.GamepadMapping;

/**
 * PSP with FuSa plugin.
 *
 * @author Ka Wing Chin
 */
public class PSPFuSa extends GamepadMapping
{	
	// Not used
	public int BUTTON_VOLUME_MIN = 9;
	public int BUTTON_VOLUME_PLUS = 10;
	public int BUTTON_BRIGHTNESS = 11;

	public PSPFuSa()
	{
		super("FuSa GamePad");
		BUTTON_B = 0; // CROSS
		BUTTON_A = 1; // CIRCLE
		BUTTON_Y = 2; // SQUARE
		BUTTON_X = 3; // TRIANGLE
		BUTTON_L1 = 4;
		BUTTON_R1 = 5;
		BUTTON_SELECT = 6;
		BUTTON_START = 7;
		BUTTON_MODE = 8;
		AXIS_LEFT_X = 0;
		AXIS_LEFT_Y = 1;
		AXIS_RIGHT_X = 3;
		AXIS_RIGHT_Y = 2;
	}
}