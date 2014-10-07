package org.flixel.examples.controller.mappings;

import org.flixel.system.input.GamepadMapping;

/**
 * Sony PLAYSTATION(R)3 Controller"
 * 
 * @author Indrek Vändrik
 */
public class PS3Controller extends GamepadMapping {
	public PS3Controller() {
		super("Sony PLAYSTATION(R)3 Controller");
		BUTTON_DPAD_UP = 4;
		BUTTON_DPAD_RIGHT = 5;
		BUTTON_DPAD_DOWN = 6;
		BUTTON_DPAD_LEFT = 7;
		BUTTON_B = 14; // CROSS
		BUTTON_A = 13; // CIRCLE
		BUTTON_Y = 15; // SQUARE
		BUTTON_X = 12; // TRIANGLE
		BUTTON_L1 = 10;
		BUTTON_R1 = 11;
		BUTTON_L2 = 8;
		BUTTON_R2 = 9;
		BUTTON_L3 = 1;
		BUTTON_R3 = 2;
		BUTTON_SELECT = 0;
		BUTTON_START = 3;
		BUTTON_MODE = 16;

		// Analog axis
		AXIS_LEFT_X = 0;
		AXIS_LEFT_Y = 1;
		AXIS_RIGHT_X = 2;
		AXIS_RIGHT_Y = 3;
	}
}
