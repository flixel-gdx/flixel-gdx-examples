package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.dynamics.joints.B2FlxRopeJoint;

/**
 *
 * @author Ka Wing Chin
 */
public class TestRopeJoint extends Test
{
	@Override
	public void create()
	{		
		super.create();
		title.setText("RopeJoint");
		info.setText("The rope joint enforces a \n" +
				"maximum distance like the \n" +
				"distance joint, but does not \n" +
				"enforce a minimum distance.");
		
		B2FlxBox box1 = createBox(FlxG.width/2-25, FlxG.height/2-25, 50, 50);
		add(box1);
		
		B2FlxBox box2 = createBox(box1.x+100, FlxG.height/2-25, 50, 50);
		add(box2);
		
		B2FlxRopeJoint joint = new B2FlxRopeJoint(box2, box1)
			.setMaxLength(7)
			.setShowLine(true)
			.setCollideConnected(true)
			.create();
		add(joint);
	}
}

