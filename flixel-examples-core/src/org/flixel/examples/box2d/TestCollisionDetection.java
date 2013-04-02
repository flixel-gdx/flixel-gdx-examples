package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.examples.box2d.objects.Ghost;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;
import org.flixel.plugin.flxbox2d.dynamics.B2FlxListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import flash.display.BlendMode;

/**
 *
 * @author Ka Wing Chin
 */
public class TestCollisionDetection extends Test
{
	private final String ImgPlayer = "examples/box2d/pack:player";
	private final String ImgBoss = "examples/box2d/pack:boss";
	private final String ImgFriendly = "examples/box2d/pack:bot";
	private final String ImgEnemy = "examples/box2d/pack:enemy";
	
	private B2FlxBox _player;
	private B2FlxBox _boss;
	private Ghost _ghost2;
	
	// Categories must be by power of 2.
	// WALL is 0x0001, which is 1. See the Test.class.
	private final short WALL = 0x0001; 		// 1
	private final short PLAYER = 0x0002; 	// 2
	private final short BOSS = 0x0004; 		// 4
	private final short FRIENDLY = 0x0008; 	// 8
	private final short ENEMY = 0x0010; 	// 16
	private final short GHOST = 0x0020; 	// 32
	
	@Override
	public void create()
	{
		super.create();
		title.setText("Collision Detection");
		info.setText("Collision and overlap");
			
		setGravity(0, 0);
		FlxG.setBgColor(0xff0076a3);
		
		// Player will collide against wall, boss and enemy, assigned in group 1.
		add(_player = createBox(8, 200, 64, 64, PLAYER, (short) (WALL | BOSS | ENEMY),  (short) 1));
		_player.ID = 23;
		_player.loadGraphic(ImgPlayer);
		
		// Boss will collide against wall, player and friendly, assigned in group 1.
		add(_boss = createBox(100, 200, 64, 64, BOSS, (short) (WALL | PLAYER | FRIENDLY), (short) 1));
		_boss.loadGraphic(ImgBoss);
		
		float x, y;
		B2FlxBox s;
		for(int i = 0; i < 5; i++)
		{
			x = FlxG.random() * 350 + 50;
			y = FlxG.random() * 350 + 50;
			// Friendly will collide against wall, boss and enemy, assigned in group 0.
			add(s = createBox(x, y, 16, 16, FRIENDLY, (short) (WALL | BOSS | ENEMY | GHOST), (short) 0));
			s.loadGraphic(ImgFriendly);	
			
			x = FlxG.random() * 300 + 50;
			y = FlxG.random() * 300 + 50;
			// Enemy will collide against wall, player and enemy, assigned in group 0.
			add(s = createBox(x, y, 16, 16, ENEMY, (short) (WALL | PLAYER | FRIENDLY | GHOST), (short) 0));
			s.loadGraphic(ImgEnemy);
		}
		
		// Ghost shows opacity when collide in the same group.
		// White ghost doesn't collide with anything, not even walls.
		// The ghost will always be sprite2 in the demo. Why? Because it's added as last. 
		// Pretty strange he. Take a look at the callbacks below.
		add(new Ghost(20, 20, GHOST, (short) 0, (short) 1, true));
		// Purple ghost collides with walls, friendly and enemy, but not the player and boss.
		add(_ghost2 = new Ghost(180, 20, GHOST, (short) (WALL | FRIENDLY | ENEMY), (short) 1, false));
		_ghost2.setColor(0xFF800080);
		
		
		// Player vs Boss
		contact.onBeginContact(_player, _boss, applyBlend);
		contact.onEndContact(_player, _boss, removeBlend);
		
		// Player vs Friendly
		contact.onBeginContact(_player, ENEMY, applyBlend);
		contact.onEndContact(_player, ENEMY, removeBlend);
		
		// Boss vs Friendly
		contact.onBeginContact(_boss, FRIENDLY, applyBlend);
		contact.onEndContact(_boss, FRIENDLY, removeBlend);
						
		// Player vs Ghost
		contact.onBeginContact(_player, _ghost2, beginGhost);
		contact.onPreSolve(_player, _ghost2, preGhost);
		contact.onPostSolve(_player, _ghost2, postGhost);
		contact.onEndContact(_player, _ghost2, endGhost);

		// Boss vs Ghost
		contact.onBeginContact(_boss, _ghost2, beginGhost);
		contact.onPreSolve(_boss, _ghost2, preGhost);
		contact.onPostSolve(_boss, _ghost2, postGhost);
		contact.onEndContact(_boss, _ghost2, endGhost);
		
		// Friendly vs Enemy
		contact.onBeginContact(FRIENDLY, ENEMY, applyBlend);
		contact.onEndContact(FRIENDLY, ENEMY, removeBlend);
	}
		
	public B2FlxBox createBox(float x, float y, float width, float height, short categoryBits, short maskBits, short groupIndex, boolean sensor)
	{
		return new B2FlxBox(x, y, width, height)
		.setFriction(.8f)
		.setRestitution(.3f)
		.setDensity(.7f)
		.setGroupIndex(groupIndex)
		.setCategoryBits(categoryBits)
		.setMaskBits(maskBits)
		.setSensor(sensor)
		.setDraggable(true)
		.create();
	}
	
	public B2FlxBox createBox(float x, float y, float width, float height, short categoryBits, short maskBits, short groupIndex)
	{
		return createBox(x, y, width, height, categoryBits, maskBits, groupIndex, false);
	}
	
	
	B2FlxListener beginPlayerBoss = new B2FlxListener()
	{			
		@Override
		public void beginContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact) 
		{
			sprite1.blend = BlendMode.LINEAR_DODGE;
			sprite2.blend = BlendMode.LINEAR_DODGE;
		}
	};
	
	B2FlxListener beginGhost = new B2FlxListener()
	{
		@Override
		public void beginContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact)
		{
			_ghost2.addOverlap();
			_ghost2.setAlpha(0.5f);
		}
	};
	
	B2FlxListener preGhost = new B2FlxListener()
	{
		@Override
		public void preSolve(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact, Manifold oldManifold) 
		{
			contact.setEnabled(false);
		}
	};
	
	B2FlxListener postGhost = new B2FlxListener()
	{		
		@Override
		public void postSolve(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact, ContactImpulse impulse) 
		{			
			contact.setEnabled(true);
		}
	};
	
	B2FlxListener endGhost = new B2FlxListener()
	{
		@Override
		public void endContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact) 
		{
			_ghost2.removeOverlap();
			if(!_ghost2.gotOverlaps())
				_ghost2.setAlpha(1f);
		}
	};
		
	B2FlxListener endPlayerBoss = new B2FlxListener()
	{		
		@Override
		public void endContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact) 
		{
			sprite1.blend = BlendMode.NORMAL;
			sprite2.blend = BlendMode.NORMAL;
		}
	};
	
	B2FlxListener applyBlend = new B2FlxListener()
	{
		@Override
		public void beginContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact)
		{
			sprite1.blend = BlendMode.LINEAR_DODGE;
			sprite2.blend = BlendMode.LINEAR_DODGE;
		}
	};
	
	B2FlxListener removeBlend = new B2FlxListener()
	{
		@Override
		public void endContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact) 
		{
			sprite1.blend = BlendMode.NORMAL;
			sprite2.blend = BlendMode.NORMAL;
		}
	};
	
	@Override
	public void destroy() 
	{
		super.destroy();
		_player = null;
		_boss = null;
		_ghost2 = null;		
		applyBlend = null;
		removeBlend = null;
		beginPlayerBoss = null;
		endPlayerBoss = null;
		beginGhost = null;
		endGhost = null;
		postGhost = null;
		preGhost = null;
	}
}

