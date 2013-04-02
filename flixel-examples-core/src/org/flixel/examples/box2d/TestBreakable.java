package org.flixel.examples.box2d;

import org.flixel.plugin.flxbox2d.B2FlxB;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxSprite;
import org.flixel.plugin.flxbox2d.common.math.B2FlxMath;
import org.flixel.plugin.flxbox2d.dynamics.B2FlxListener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 *
 * @author Ka Wing Chin
 */
public class TestBreakable extends Test
{
	private B2FlxShape _box;
	private Vector2 _velocity = new Vector2();
	private float _angularVelocity;
	private B2FlxBox _shape1;
	private B2FlxBox _shape2;
	private Fixture _piece1;
	private Fixture _piece2;
	private boolean _broke;
	private boolean _break;
	private final short WALL = 0x0001; 	
	
	@Override
	public void create()
	{
		super.create();
		title.setText("Breakable");
		info.setText("Smash the box against the wall to break.");
		
		_box = new B2FlxSprite(150, 150)
			.setDensity(1)
			.setAngle(45)
			.setDraggable(true)
			.create();		
		add(_box);
		
		// Breakable Dynamic Body
		_shape1 = new B2FlxBox(0, 0, 30, 30, new Vector2(-.5f, 0));
		_shape2 = new B2FlxBox(0, 0, 30, 30, new Vector2(.5f, 0));
		
		_piece1 = _box.createFixture(_shape1, 1);
		_piece2 = _box.createFixture(_shape2, 1);
		
		_broke = false;
		_break = false;
		
		contact.onPostSolve(_box, WALL, post);
	}
	
	@Override
	public void update()
	{
		super.update();
		if(_break)
		{
			breakBody();
			_broke = true;
			_break = false;
		}
		
		if(!_broke)
		{
			_velocity.set(_box.body.getLinearVelocity());
			_angularVelocity = _box.body.getAngularVelocity();
		}
	}
	
	public void breakBody()
	{
		// Create two bodies from one.
		Body body1 = _piece1.getBody();
//		Vector2 center = body1.getWorldCenter();
		
		body1.destroyFixture(_piece2);
		_piece2 = null;
		
		float x = body1.getPosition().x * B2FlxB.RATIO;
		float y = body1.getPosition().y * B2FlxB.RATIO;
		
		// Shapeless
		B2FlxSprite body2 = new B2FlxSprite(x, y)
			.setAngle(body1.getAngle())
			.setDraggable(true)
			.create();
		body2.createFixture(_shape2, 1.0f);
		add(body2);
		
		// Compute consistent velocities for new bodies based on cached velocity.
//		Vector2 center1 = body1.getWorldCenter();
//		Vector2 center2 = body2.body.getWorldCenter();

//		Vector2 velocity1 = velocity.add(B2FlxV2.cross(angularVelocity, center1.sub(center)));
//		Vector2 velocity2 = velocity.add(B2FlxV2.cross(angularVelocity, center2.sub(center)));

		body1.setAngularVelocity(_angularVelocity);
//		body1.setLinearVelocity(velocity1);

		body2.setAngularVelocity(_angularVelocity);
//		body2.setLinearVelocity(velocity2);
		
	}
	
	B2FlxListener post = new B2FlxListener()
	{
		@Override
		public void postSolve(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact, ContactImpulse impulse)
		{
			if(_broke)
			{
				// The body already broke.
				return;
			}
			
			// Should the body break?
			int count = contact.getWorldManifold().getNumberOfContactPoints();
			
			float maxImpulse = 0.0f;
			for(int i = 0; i < count; i++)
			{
				maxImpulse = B2FlxMath.max(maxImpulse, impulse.getNormalImpulses()[i]);
			}
			
			if(maxImpulse > 40.0f)
			{
				_break = true;
			}
		}
	};
	
	@Override
	public void destroy() 
	{
		super.destroy();
		_box = null;
		_velocity = null;
		_shape1 = null;
		_shape2 = null;
		_piece1 = null;
		_piece2 = null;
	}
}