package org.flixel.examples.controller;

import org.flixel.FlxGroup;
import org.flixel.FlxSprite;
import org.flixel.system.input.Gamepad;

public class Player extends FlxSprite
{
	protected String ImgSpaceman = "examples/controller/pack:spaceman";
	
	private Gamepad _gamepad;
	protected int _jumpPower;
	protected FlxGroup _bullets;
	protected int _aim;
	
	public Player(float X, float Y, int color, FlxGroup bullets, Gamepad gamepad)
	{
		super(X, Y);
		_gamepad = gamepad;
		_bullets = bullets;
		Bullet b;
		for(int i = 0; i < 3; i++)
		{
			_bullets.add(b = new Bullet());
			b.kill();
		}
		
		loadGraphic(ImgSpaceman,true,true,8);
		setColor(color);
		//bounding box tweaks
		width = 6;
		height = 7;
		offset.x = 1;
		offset.y = 1;

		//basic player physics
		int runSpeed = 80;
		drag.x = runSpeed*8;
		acceleration.y = 420;
		_jumpPower = 200;
		maxVelocity.x = runSpeed;
		maxVelocity.y = _jumpPower;

		//animations
		addAnimation("idle", new int[]{0});
		addAnimation("run", new int[]{1, 2, 3, 0}, 12);
		addAnimation("jump", new int[]{4});
		addAnimation("idle_up", new int[]{5});
		addAnimation("run_up", new int[]{6, 7, 8, 5}, 12);
		addAnimation("jump_up", new int[]{9});
		addAnimation("jump_down", new int[]{10});

	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		_gamepad = null;
	}
	
	@Override
	public void update()
	{
		//MOVEMENT
		acceleration.x = 0;
		if(_gamepad.LEFT || _gamepad.UP_LEFT || _gamepad.DOWN_LEFT)
		{
			setFacing(LEFT);
			acceleration.x -= drag.x;
		}
		else if(_gamepad.RIGHT || _gamepad.UP_RIGHT || _gamepad.DOWN_RIGHT)
		{
			setFacing(RIGHT);
			acceleration.x += drag.x;
		}
		if(_gamepad.justPressed("BUTTON_A") && (int) velocity.y == 0)
		{
			velocity.y = -_jumpPower;
		}

		//AIMING
		if(_gamepad.UP || _gamepad.UP_RIGHT || _gamepad.UP_LEFT)
			_aim = UP;
		else if((_gamepad.DOWN || _gamepad.DOWN_RIGHT || _gamepad.DOWN_LEFT) && velocity.y != 0)
			_aim = DOWN;
		else
			_aim = getFacing();

		//ANIMATION
		if(velocity.y != 0)
		{
			if(_aim == UP) play("jump_up");
			else if(_aim == DOWN) play("jump_down");
			else play("jump");
		}
		else if(velocity.x == 0)
		{
			if(_aim == UP) play("idle_up");
			else play("idle");
		}
		else
		{
			if(_aim == UP) play("run_up");
			else play("run");
		}

		//SHOOTING
		if(_gamepad.justPressed("BUTTON_B"))
		{
			if(getFlickering())
				return;
			else
			{
				getMidpoint(_point);
				Bullet bullet = (Bullet) _bullets.getFirstAvailable(Bullet.class);
				if(bullet != null)
					bullet.shoot(_point,_aim);
				if(_aim == DOWN)
					velocity.y -= 36;
			}
		}
	}
	
	@Override
	public void hurt(float Damage)
	{
		super.hurt(Damage);
		flicker();
	}
}
