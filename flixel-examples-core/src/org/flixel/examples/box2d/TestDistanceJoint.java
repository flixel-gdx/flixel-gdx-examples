package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.dynamics.joints.B2FlxDistanceJoint;

/**
 *
 * @author Ka Wing Chin
 */
public class TestDistanceJoint extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("DistanceJoint");
		info.setText("The distance joint enforces a \ndistance between two bodies.");
		
		B2FlxBox box1 = createBox(FlxG.width/2-25, FlxG.height/2-25, 50, 50);
		add(box1);
		
		B2FlxBox box2 = createBox(box1.x+100, FlxG.height/2-25, 50, 50);
		add(box2);
		
		B2FlxDistanceJoint joint = (B2FlxDistanceJoint) new B2FlxDistanceJoint(box1, box2)
			.setAnchorA(box1.body.getWorldCenter())
			.setAnchorB(box2.body.getWorldCenter())
			.setShowLine(true)
			.create();
		add(joint);
	}
}

