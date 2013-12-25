package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.dynamics.joints.B2FlxWeldJoint;

/**
 *
 * @author Ka Wing Chin
 */
public class TestWeldJoint extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("WeldJoint");
		info.setText("The weld joint joins two \nbodies together.");
		
		B2FlxBox box1 = createBox(FlxG.width/2-25, FlxG.height/2-25, 50, 50);
		add(box1);
		
		B2FlxBox box2 = createBox(box1.x+25, FlxG.height/2-50, 50, 50);
		add(box2);
		
		new B2FlxWeldJoint(box1, box2)
			.setAnchorA(box1.body.getWorldCenter())
			.create();
	}
}

