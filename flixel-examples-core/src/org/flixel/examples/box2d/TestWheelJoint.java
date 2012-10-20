package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;
import org.flixel.plugin.flxbox2d.dynamics.joints.B2FlxWheelJoint;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Ka Wing Chin
 */
public class TestWheelJoint extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("WheelJoint");
		info.setText("This joint is designed for vehicle suspensions.");
		
		B2FlxCircle circle = (B2FlxCircle) new B2FlxCircle(100, FlxG.height/2-20, 20)
			.setType(B2FlxShape.DYNAMIC)
			.setFriction(.8f)
			.setRestitution(.3f)
			.setDensity(.7f)
			.setDraggable(true)
			.create();
		add(circle);
		
		B2FlxCircle circle2 = (B2FlxCircle) new B2FlxCircle(150, FlxG.height/2-20, 20)
			.setFriction(.8f)
			.setRestitution(.3f)
			.setDensity(.7f)
			.setDraggable(true)
			.create();
		add(circle2);
		
		B2FlxBox box2 = createBox(115, FlxG.height/2-100, 50, 50);
		add(box2);
		
		new B2FlxWheelJoint(null, circle)
			.setAxis(new Vector2(1, 0))
			.setMotorSpeed(10)
			.setMaxMotorTorque(20)
			.setEnableMotor(true)
			.create();
		
		new B2FlxWheelJoint(null, circle2)
			.setAxis(new Vector2(1, 0))
			.setMotorSpeed(-10)
			.setMaxMotorTorque(20)
			.setEnableMotor(true)
			.create();
	}
}

