package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.dynamics.joints.B2FlxRevoluteJoint;

/**
 *
 * @author Ka Wing Chin
 */
public class TestRevoluteJoint extends Test
{
	@Override
	public void create()
	{		
		super.create();
		title.setText("RevoluteJoint");
		info.setText("The revolute joint causes a\n" +
				"body to be pinned on a\n" +
				"anchor point. In this example\n" +
				"friction has been added.");
		
		B2FlxBox box1 = createBox(FlxG.width/2-25, FlxG.height/2-25, 50, 50);
		add(box1);
		
		B2FlxBox box2 = createBox(box1.x+100, FlxG.height/2-25, 50, 50);
		add(box2);
		
		B2FlxRevoluteJoint joint = new B2FlxRevoluteJoint(box1, null)
			.setMaxMotorTorque(1)
			.setEnableMotor(true)
			.setAnchorA(box1.body.getWorldCenter())
			.create();
		add(joint);
	}
}

