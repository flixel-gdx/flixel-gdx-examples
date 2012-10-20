package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.examples.box2d.objects.RagDoll;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 * @author Ka Wing Chin
 */
public class TestRagdolls extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("Ragdolls");
		
		add(new RagDoll(70 + FlxG.random() * 20, 20 + FlxG.random() * 50));
		add(new RagDoll(70 + FlxG.random() * 20 + 440, 20 + FlxG.random() * 50));
		
		
		// Add stairs on the left, these are static bodies so set the type accordingly
		FixtureDef fd = new FixtureDef();
		fd.density = 0;
		fd.friction = .4f;
		fd.restitution = .3f;
		
		B2FlxBox step;
		for(int j = 1; j < 10; j++)
		{
			step = (B2FlxBox) new B2FlxBox(0, 160+20*j, 20*j, 20)
				.setType(B2FlxShape.STATIC)
				.create();
			add(step);
		}
		
		// Add stairs on the right
		for(int j = 1; j < 10; j++)
		{
			step = (B2FlxBox) new B2FlxBox(FlxG.width-20*j, 160+20*j, 20*j, 20)
				.setType(B2FlxShape.STATIC)
				.create();
			add(step);
		}
		
		// A block in the center
		add(new B2FlxBox(290, 280, 60, 80).setType(B2FlxShape.STATIC).create());
	}
}

