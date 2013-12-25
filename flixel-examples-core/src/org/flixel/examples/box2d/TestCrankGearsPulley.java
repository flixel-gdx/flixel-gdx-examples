package org.flixel.examples.box2d;

import org.flxbox2d.B2FlxB;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.collision.shapes.B2FlxCircle;
import org.flxbox2d.common.B2FlxV2;
import org.flxbox2d.dynamics.joints.B2FlxFrictionJoint;
import org.flxbox2d.dynamics.joints.B2FlxGearJoint;
import org.flxbox2d.dynamics.joints.B2FlxPrismaticJoint;
import org.flxbox2d.dynamics.joints.B2FlxPulleyJoint;
import org.flxbox2d.dynamics.joints.B2FlxRevoluteJoint;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Ka Wing Chin
 */
public class TestCrankGearsPulley extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("Joints");
		
		// CRANK
		// Define crank.
		B2FlxBox crank = new B2FlxBox(100, 360-135, 15, 60)
			.setDensity(1f)
			.setDraggable(true)
			.create();
		add(crank);
		
		new B2FlxRevoluteJoint(null, crank)
			.setMotorSpeed((float) (1f * -Math.PI))
			.setMaxMotorTorque(5000f)
			.setEnableMotor(true)
			.setAnchorA(new B2FlxV2(100+crank.center.x, 360-75))
			.create();
		
		
		// Define follower.
		B2FlxBox follower = new B2FlxBox(100, 360-255, 15, 120)
			.setDensity(1)
			.setDraggable(true)
			.create();
		add(follower);
		
		new B2FlxRevoluteJoint(crank, follower)
			.setAnchorA(new B2FlxV2(100+crank.center.x, 360-135))
			.create();
		
		// Define piston
		B2FlxBox piston = new B2FlxBox(100-follower.width, 360-277.5f, 45, 45)
			.setDensity(1f)
			.setDraggable(true)
			.create();
		add(piston);
		
		new B2FlxRevoluteJoint(follower, piston)
			.setAnchorA(new B2FlxV2(crank.x+crank.center.x, 360-255))
			.create();
		
		new B2FlxPrismaticJoint(null, piston)
			.setAxis(new Vector2(0,1))
			.setMaxMotorForce(500)
			.setEnableMotor(true)
			.setAnchorA(piston.body.getWorldCenter())
			.create();
		
		// Create a payload
		B2FlxBox payload = new B2FlxBox(100-follower.width, 360-367.5f, 45, 45)
			.setDensity(2f)
			.setDraggable(true)
			.create();
		add(payload);
		
		
		// GEARS
		B2FlxCircle circle1 = new B2FlxCircle(175, 155, 25)
			.setDensity(5)
			.setDraggable(true)
			.create();
		add(circle1);
		
		B2FlxRevoluteJoint joint1 = new B2FlxRevoluteJoint(null, circle1)
			.setAnchorA(circle1.body.getPosition())
			.create();
		
		B2FlxCircle circle2 = new B2FlxCircle(225, 130, 50)
			.setDensity(5)
			.setDraggable(true)
			.create();
		add(circle2);
		
		B2FlxRevoluteJoint joint2 = new B2FlxRevoluteJoint(null, circle2)
			.setAnchorA(circle2.body.getPosition())
			.create();
		
		B2FlxBox box = new B2FlxBox(325, 80, 20, 200)
			.setDensity(5)
			.setDraggable(true)
			.create();
		add(box);
				
		B2FlxPrismaticJoint joint3 = new B2FlxPrismaticJoint(null, box)
			.setAxis(new Vector2(0, 1))
			.setLowerTranslation(-25f/B2FlxB.RATIO)
			.setUpperTranslation(100f/B2FlxB.RATIO)
			.setEnableLimit(true)
			.setAnchorA(box.body.getPosition())
			.create();
		
		
		new B2FlxGearJoint(circle1, circle2)
			.setJoint1(joint1.joint)
			.setJoint2(joint2.joint)
			.setRatio(circle2.getRadius() / circle1.getRadius())
			.create();
		
		new B2FlxGearJoint(circle2, box)
			.setJoint1(joint2.joint)
			.setJoint2(joint3.joint)
			.setRatio(-1f / circle2.getRadius())
			.create();
		
		
		// PULLEY
		B2FlxBox pulley = new B2FlxBox(430, 180, 100, 40)
			.setDensity(5)
			.setDraggable(true)
			.create();
		add(pulley);
		
		new B2FlxPulleyJoint(box, pulley)
			.setGroundAnchorA(new B2FlxV2(335, 50))
			.setGroundAnchorB(new B2FlxV2(480, 50))
			.setRatio(1)
			.setAnchorA(new B2FlxV2(335, 180))
			.setAnchorB(new B2FlxV2(480, 180))
			.create();

		
		// FRICTION JOINT
		// Add a circle to weigh down the pulley
		B2FlxCircle circ = new B2FlxCircle(445, 60, 40)
			.setFriction(.3f)
			.setRestitution(.3f)
			.setDensity(5f)
			.setDraggable(true)
			.create();
		add(circ);
		
		new B2FlxFrictionJoint(null, circ)
			.setMaxForce(200)
			.setCollideConnected(true)
			.setAnchorA(circ.body.getPosition())
			.create();
	}
}

