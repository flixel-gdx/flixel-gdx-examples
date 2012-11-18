package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.examples.box2d.objects.Ghost;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;
import org.flixel.plugin.flxbox2d.dynamics.B2FlxContactEvent;
import org.flixel.plugin.flxbox2d.dynamics.B2FlxListener;

import com.badlogic.gdx.physics.box2d.Contact;

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
	private B2FlxBox _friendly;
	private B2FlxBox _enemy;
	private Ghost _ghost;
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
			
		contactListener.addEventListener(B2FlxContactEvent.BEGIN, beginContact);
		contactListener.addEventListener(B2FlxContactEvent.PRESOLVE, preSolve);
		contactListener.addEventListener(B2FlxContactEvent.POSTSOLVE, postSolve);
		contactListener.addEventListener(B2FlxContactEvent.END, endContact);
		
		// Player will collide against wall, boss and enemy, assigned in group 1.
		add(_player = createBox(8, 200, 64, 64, PLAYER, (short) (WALL | BOSS | ENEMY),  (short) 1));
		_player.loadGraphic(ImgPlayer);
		// Boss will collide against wall, player and friendly, assigned in group 1.
		add(_boss = createBox(100, 200, 64, 64, BOSS, (short) (WALL | PLAYER | FRIENDLY), (short) 1));
		_boss.loadGraphic(ImgBoss);
		// Friendly will collide against wall, boss and enemy, assigned in group 0.
		add(_friendly = createBox(200, 200, 16, 16, FRIENDLY, (short) (WALL | BOSS | ENEMY | GHOST), (short) 0));
		_friendly.loadGraphic(ImgFriendly);
		// Enemy will collide against wall, player and enemy, assigned in group 0.
		add(_enemy = createBox(300, 200, 16, 16, ENEMY, (short) (WALL | PLAYER | FRIENDLY | GHOST), (short) 0));
		_enemy.loadGraphic(ImgEnemy);
		
		// Ghost shows opacity when collide in the same group.
		// White ghost doesn't collide with anything, not even walls.
		// The ghost will always be sprite2 in the demo. Why? Because it's added as last. 
		// Pretty strange he. Take a look at the callbacks below.
		add(_ghost = new Ghost(20, 20, GHOST, (short) 0, (short) 1, true));
		// Purple ghost collides with walls, friendly and enemy, but not the player and boss.
		add(_ghost2 = new Ghost(180, 20, GHOST, (short) (WALL | FRIENDLY | ENEMY), (short) 1, false));
		_ghost2.setColor(0xFF800080);
	}
		
	public B2FlxBox createBox(float x, float y, float width, float height, short categoryBits, short maskBits, short groupIndex, boolean sensor)
	{
		return (B2FlxBox) new B2FlxBox(x, y, width, height)
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
	
	
	B2FlxListener beginContact = new B2FlxListener()
	{			
		@Override
		public void onContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact) 
		{			
			if(sprite1 == _ghost || sprite2 == _ghost)
			{
				_ghost.addOverlap();
				_ghost.setAlpha(0.5f);
			}
			else if((sprite1 == _ghost2 || sprite2 == _ghost2) && (sprite1.categoryBits == PLAYER || sprite1.categoryBits == BOSS))
			{
				_ghost2.addOverlap();
				_ghost2.setAlpha(0.5f);
			}
			else if(sprite1.categoryBits == PLAYER || sprite1.categoryBits == BOSS)
			{
				sprite1.blend = BlendMode.LINEAR_DODGE;
				sprite2.blend = BlendMode.LINEAR_DODGE;
			}
		}
	};
	
	B2FlxListener preSolve = new B2FlxListener()
	{
		@Override
		public void onContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact) 
		{
			if((sprite2 == _ghost2 ) && (sprite1.categoryBits == PLAYER || sprite1.categoryBits == BOSS))
			{
				contact.setEnabled(false);
			}
		}		
	};
	
	B2FlxListener postSolve = new B2FlxListener()
	{
		@Override
		public void onContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact) 
		{			
			if(sprite1 == _ghost2 || sprite2 == _ghost2)
			{
				contact.setEnabled(true);
			}
		}
	};
		
	B2FlxListener endContact = new B2FlxListener()
	{		
		@Override
		public void onContact(B2FlxShape sprite1, B2FlxShape sprite2, Contact contact) 
		{			
			if(sprite2 == _ghost && (sprite1.categoryBits == PLAYER || sprite1.categoryBits == BOSS))
			{
				_ghost.removeOverlap();
				if(!_ghost.gotOverlaps())
					_ghost.setAlpha(1f);
			}
			else if(sprite2 == _ghost2  && (sprite1.categoryBits == PLAYER || sprite1.categoryBits == BOSS))
			{				
				_ghost2.removeOverlap();
				if(!_ghost2.gotOverlaps())
					_ghost2.setAlpha(1f);
			}
			else if(sprite1.categoryBits == PLAYER || sprite1.categoryBits == BOSS)
			{
				sprite1.blend = BlendMode.NORMAL;
				sprite2.blend = BlendMode.NORMAL;
			}
		}
	};
}

