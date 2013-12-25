package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flxbox2d.B2FlxB;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.common.math.B2FlxMath;
import org.flxbox2d.dynamics.joints.B2FlxGearJoint;
import org.flxbox2d.dynamics.joints.B2FlxPrismaticJoint;
import org.flxbox2d.dynamics.joints.B2FlxRevoluteJoint;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Ka Wing Chin
 */
public class TestGearJoint extends Test
{

	@Override
	public void create()
	{
		super.create();
		title.setText("GearJoint");
		info.setText("As one box rotates, the other \nthe other slides and vice versa.");
		
		B2FlxBox box1 = createBox(FlxG.width/2-100, FlxG.height/2-25, 50, 50).setDraggable(true);
		add(box1);
		
		B2FlxBox box2 = createBox(box1.x+100, FlxG.height/2-25, 50, 50).setDraggable(true);
		add(box2);
				
		B2FlxRevoluteJoint revoluteJoint = new B2FlxRevoluteJoint(null, box1)
			.setLowerAngle(-90)
			.setUpperAngle(45)
			.setEnableLimit(true)
			.setMaxMotorTorque(10)
			.setMotorSpeed(0)
			.setAnchorA(box1.body.getWorldCenter())
			.create();
		
		B2FlxPrismaticJoint prismaticJoint = new B2FlxPrismaticJoint(null, box2)
			.setAxis(new Vector2(1, 0))
			.setLowerTranslation(-5f)
			.setUpperTranslation(2.5f)
			.setEnableLimit(true)
			.setMaxMotorForce(1)
			.setMotorSpeed(0)
			.setEnableMotor(true)
			.setAnchorA(box2.body.getWorldCenter())
			.create();
		
		add(new B2FlxGearJoint(box1, box2)
			.setJoint1(revoluteJoint.joint)
			.setJoint2(prismaticJoint.joint)
			.setRatio(2 * B2FlxMath.PI / (300 / B2FlxB.RATIO))
			.create());
	}
}

