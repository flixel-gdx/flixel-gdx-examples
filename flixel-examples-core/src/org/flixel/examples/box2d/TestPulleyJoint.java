package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flxbox2d.B2FlxB;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.dynamics.joints.B2FlxPulleyJoint;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Ka Wing Chin
 */
public class TestPulleyJoint extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("PulleyJoint");
		info.setText("Drag a box up or down and/n observe the effect.");
		
		B2FlxBox box1 = createBox(FlxG.width/2-25, FlxG.height/2-25, 50, 50);
		add(box1);
		
		B2FlxBox box2 = createBox(box1.x+100, FlxG.height/2-25, 50, 50);
		add(box2);
		
		/**
		 * g1___g2
		 * |	 |
		 * |	 |
		 * |	 |
		 *[a1]  [a2]
		 */
		Vector2 anchor1 = box1.body.getWorldCenter();
		Vector2 anchor2 = box2.body.getWorldCenter();
		Vector2 groundAnchor1 = new Vector2(anchor1.x, anchor1.y - (150f / B2FlxB.RATIO));
		Vector2 groundAnchor2 = new Vector2(anchor2.x, anchor2.y - (150f / B2FlxB.RATIO));
		
		add(new B2FlxPulleyJoint(box1, box2)
			.setGroundAnchorA(groundAnchor1)
			.setGroundAnchorB(groundAnchor2)
			.setRatio(1)
			.setAnchorA(anchor1)
			.setAnchorB(anchor2)
			.setShowLine(true)
			.create());
	}
}

