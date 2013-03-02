package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxPolygon;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 * @author Ka Wing Chin
 */
public class TestStack extends Test
{
	@Override
	public void create()
	{		
		super.create();
		title.setText("Stack");
		
		// Fixture definition
		FixtureDef fd = new FixtureDef();
		fd.density = 1f;
		fd.friction = .5f;
		fd.restitution = .1f;
		
		// Create 3 stacks
		int i;
		for(i = 0; i < 10; i++)
		{
			add(new B2FlxBox(620/2f + FlxG.random() * .02f - .01f, 360 - 20 - i * 25, 20, 20).setFixtureDef(fd).setDraggable(true).create());
			add(new B2FlxBox(620/2f + 100, 360 - 20 - i * 25, 20, 20).setFixtureDef(fd).setDraggable(true).create());
			add(new B2FlxBox(620/2f + 200 + FlxG.random() * .02f - .01f, 360 - 20 - i * 25, 20, 20).setFixtureDef(fd).setDraggable(true).create());
		}
		
		// Create ramp
		B2FlxPolygon ramp = (B2FlxPolygon) new B2FlxPolygon(0, 360, new float[][][]{{{0,0}, {0,-100}, {200,0}}})
			.setDensity(0)
			.setType(B2FlxShape.STATIC)
			.create();
		add(ramp);
		
		
		// Create ball
		B2FlxCircle ball = new B2FlxCircle(10, 60, 40)
			.setDensity(2f)
			.setRestitution(.2f)
			.setFriction(.5f)
			.setDraggable(true)
			.create();
		add(ball);
	}
}

