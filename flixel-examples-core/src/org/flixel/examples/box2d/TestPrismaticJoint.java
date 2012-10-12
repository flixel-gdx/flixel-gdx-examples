package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.dynamics.joints.B2FlxPrismaticJoint;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Ka Wing Chin
 */
public class TestPrismaticJoint extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("PrismaticJoint");
		info.setText("The prismatic joint can act \nlike a moving platform.");
		
		B2FlxBox box = createBox(FlxG.width/2-25, FlxG.height/2-25, 50, 50);
		add(box);
		
		B2FlxPrismaticJoint joint = (B2FlxPrismaticJoint) new B2FlxPrismaticJoint(box, null)
			.setAxis(new Vector2(0,1))
			.setLowerTranslation(-5f)
			.setUpperTranslation(2.5f)
			.setEnableLimit(true)
			.setMaxMotorForce(1f)
			.setMotorSpeed(0)
			.setEnableMotor(true)
			.setAnchorA(box.body.getWorldCenter())
			.create();
		add(joint);
	}
}

