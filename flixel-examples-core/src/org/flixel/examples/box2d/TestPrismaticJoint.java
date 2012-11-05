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
		
		B2FlxBox box = createBox(50, FlxG.height/2-25, 50, 50);
		add(box);
		
		new B2FlxPrismaticJoint(box, null)
			.setAxis(new Vector2(0, 1))
			.setLowerTranslation(-5f)
			.setUpperTranslation(2.5f)
			.setMaxMotorForce(50f)
			.setMotorSpeed(80)
			.setEnableLimit(true)
			.setEnableMotor(true)
			.setAnchorA(box.body.getWorldCenter())
			.create();
		
		box = createBox(FlxG.width/2f, FlxG.height/2-25, 50, 50);
		add(box);
		
		new B2FlxPrismaticJoint(box, null)
			.setAxis(new Vector2(1,0))
			.setLowerTranslation(-5f)
			.setUpperTranslation(5f)
			.setMaxMotorForce(5f)
			.setMotorSpeed(80)
			.setEnableLimit(true)
			.setEnableMotor(true)
			.setAnchorA(box.body.getWorldCenter())
			.create();
	}
}

