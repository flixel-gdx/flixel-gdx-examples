package org.flixel.examples.box2d;

import org.flixel.plugin.flxbox2d.B2FlxB;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxSprite;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 *
 * @author Ka Wing Chin
 */
public class TestBreakable extends Test
{
	public B2FlxShape body;
	public Vector2 velocity = new Vector2();
	public float angularVelocity;
	public B2FlxBox shape1;
	public B2FlxBox shape2;
	public Fixture piece1;
	public Fixture piece2;
	public boolean broke;
	public boolean _break;
	
	@Override
	public void create()
	{
		super.create();
		title.setText("Breakable");
		
		B2FlxB.world.setContactListener(new ContactListenerBreakable(this));
		
		body = new B2FlxSprite(150, 150)
			.setDensity(1)
			.setAngle(45)
			.create();
		add(body);
		
		// Breakable Dynamic Body
		shape1 = new B2FlxBox(0, 0, 30, 30, new Vector2(-.5f, 0));
		shape2 = new B2FlxBox(0, 0, 30, 30, new Vector2(.5f, 0));
		
		piece1 = body.createFixture(shape1, 1);
		piece2 = body.createFixture(shape2, 1);
		
		broke = false;
		_break = false;
	}
	
	public void breakBody()
	{
		// Apply cached velocity for more realistic break
		body.setLinearVelocity(velocity);
		body.setAngularVelocity(angularVelocity);
		
		// Split body in two pieces
		// TODO: continue here
	}
}

class ContactListenerBreakable implements ContactListener
{
	//private TestBreakable test;
	
	public ContactListenerBreakable(TestBreakable test)
	{
		//this.test = test;
	}

	@Override
	public void beginContact(Contact contact)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{
		// TODO Auto-generated method stub
		
	}
	
}