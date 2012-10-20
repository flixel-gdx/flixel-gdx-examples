package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.B2FlxB;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 *
 * @author Ka Wing Chin
 */
public class TestOneSidedPlatform extends Test
{	
	public float top;
	public float bottom;
	public int state;
	public B2FlxBox platform;
	public B2FlxCircle ball;
	
	@Override
	public void create()
	{
		super.create();
		title.setText("One Sided Platform");
		
		// Platform
		add(platform = (B2FlxBox) new B2FlxBox(FlxG.width/2-90, 285, 180, 30)
			.setType(B2FlxShape.STATIC)
			.create());
		
		bottom = platform.position.y + .5f;
		top = platform.position.y - 1.5f;
		
		// Actor
		add(ball = (B2FlxCircle) new B2FlxCircle(FlxG.width/2-15, 330, 15)
				.setDensity(1)
				.setFriction(0)
				.setRestitution(0)
				.setDraggable(true)
				.create());
		
		B2FlxB.world.setContactFilter(new ContactFilter()
		{
			
			@Override
			public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB)
			{
				if ((fixtureA == platform.fixture && fixtureB == ball.fixture) || (fixtureA == platform.fixture && fixtureB == ball.fixture)) 
				{
					Vector2 position = ball.body.getPosition();
					if (position.y > top + ball.getRadius() + 3f * 0.005f)
						return false;
					else
						return true;
				} 
				else
					return true;
			}
		});
	}
}
