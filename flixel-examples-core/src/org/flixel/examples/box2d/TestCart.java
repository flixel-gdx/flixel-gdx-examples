package org.flixel.examples.box2d;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxGamePad;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.dynamics.joints.B2FlxRevoluteJoint;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author Ka Wing Chin
 */
public class TestCart extends Test
{
	private B2FlxCircle _wheel1;
	private B2FlxCircle _wheel2;
	private FlxGamePad _pad;
	
	
	@Override
	public void create()
	{
		super.create();
		
		title.setText("Cart");
		info.setText("Use the A and D \nkeyboard buttons to drive the \ncart.");
		
		// Boxes
		add(createBox(50, 15, 50, 50));
		add(createBox(535, 15, 50, 50));
		
		// Cart
		B2FlxBox cart = new B2FlxBox(220, 20, 200, 20)
			.setDensity(1)
			.setFriction(.3f)
			.setDraggable(true)
			.create();
		add(cart);
		
		// Wheels
		add(_wheel1 = new B2FlxCircle(cart.x-20, 10, 20)
				.setDensity(1)
				.setFriction(4)
				.setDraggable(true)
				.create());
		
		add(_wheel2 = new B2FlxCircle(cart.x+cart.width-20, 10, 20)
			.setDensity(1)
			.setFriction(4)
			.setDraggable(true)
			.create());
		
		// First wheel
		new B2FlxRevoluteJoint(_wheel1, cart).setAnchorA(_wheel1.body.getWorldCenter()).create();
		
		// Second wheel
		new B2FlxRevoluteJoint(_wheel2, cart).setAnchorA(_wheel2.body.getWorldCenter()).create();
		
		FlxG.camera.follow(cart);
		
		_pad = new FlxGamePad(FlxGamePad.LEFT_RIGHT, FlxGamePad.NONE);
		if(Gdx.app.getType() == ApplicationType.Android)
		{
			_pad.setDPadPositionY(-20);
			_pad.setAlpha(0.5f);
			add(_pad);
		}
	}
	
	
	@Override
	public void update()
	{
		if(FlxG.keys.pressed("A") || _pad.buttonLeft.status == FlxButton.PRESSED)
		{				
			_wheel1.body.applyTorque(-20, true);
			_wheel2.body.applyTorque(-20, true);
		}
		else if(FlxG.keys.pressed("D") || _pad.buttonRight.status == FlxButton.PRESSED)
		{				
			_wheel1.body.applyTorque(20, true);
			_wheel2.body.applyTorque(20, true);
		}
		super.update();
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		_wheel1 = _wheel2 = null;
		_pad = null;
	}
}

