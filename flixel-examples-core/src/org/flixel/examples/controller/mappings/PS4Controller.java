package org.flixel.examples.controller.mappings;

import org.flixel.system.input.GamepadMapping;

/**
 * Sony PLAYSTATION(R)4 Controller"
 * 
 * @author Emanuel Herrera
 */
public class PS4Controller extends GamepadMapping {
	public PS4Controller() {
		super("wireless controller");	//This is the name in windows 8.1
		BUTTON_DPAD_UP = 4;
		BUTTON_DPAD_RIGHT = 5;
		BUTTON_DPAD_DOWN = 6;
		BUTTON_DPAD_LEFT = 7;
		/**
		 * Cross
		 */
		BUTTON_B = 1;
		
		/**
		 * Circle
		 */
		BUTTON_A = 2;
		
		/**
		 * Square
		 */
		BUTTON_Y = 0;
		
		/**
		 * Triangle
		 */
		BUTTON_X = 3;
		BUTTON_L1 = 4;
		BUTTON_R1 = 5;
		BUTTON_L2 = 6;
		BUTTON_R2 = 7;
		BUTTON_L3 = 10;
		BUTTON_R3 = 11;
		
		/**
		 * Share button
		 */
		BUTTON_SELECT = 8;
		
		/**
		 * The option button 
		 */
		BUTTON_START = 9;
		
		/**
		 * The touchpad
		 */
		BUTTON_MODE = 13;

		// Analog axis
		AXIS_LEFT_X = 3;
		AXIS_LEFT_Y = 2;
		AXIS_RIGHT_X = 0;
		AXIS_RIGHT_Y = 1;
	}
}
