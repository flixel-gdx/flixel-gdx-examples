package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxSprite;
import org.flixel.plugin.flxbox2d.common.B2FlxV2;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 * @author Ka Wing Chin
 */
public class TestCCD extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("Continuous Collision Detection");
		
		// These values are used for all the parts of the 'basket'
		FixtureDef fd = new FixtureDef();
		fd.density = 4;
		fd.restitution = 1.4f;
		
		
		// Create 'basket'
		B2FlxShape body = new B2FlxSprite(150, 100)
			.setBullet(true)
			.setDraggable(true)
			.create();
		
		B2FlxBox bottom = new B2FlxBox(0, 0, 90, 9).setFixtureDef(fd);
		body.createFixture(bottom.fixtureDef);
		
		B2FlxBox left = new B2FlxBox(0, 0, 9, 162, new B2FlxV2(-43.5f, -70.5f))
			.setAngle(-11.5f)
			.setFixtureDef(fd);
		body.createFixture(left.fixtureDef);
		
		B2FlxBox right = new B2FlxBox(0, 0, 9, 162, new B2FlxV2(43.5f, -70.5f))
			.setAngle(11.5f)
			.setFixtureDef(fd);		
		body.createFixture(right.fixtureDef);
		add(body);
		
		
		// add some small circles for effect
		fd.friction = .3f;
		fd.density = 1f;
		fd.restitution = 1.1f;
		for (int i = 0; i < 5; i++) 
		{
			add(new B2FlxCircle(FlxG.random()*300+250, FlxG.random()*320+20, FlxG.random()*10+5)
					.setFixtureDef(fd)
					.setBullet(true)
					.setDraggable(true)
					.create());
		}
	}
}

