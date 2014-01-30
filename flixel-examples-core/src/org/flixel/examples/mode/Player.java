package org.flixel.examples.mode;

import org.flixel.*;

public class Player extends FlxSprite
{
	protected String ImgSpaceman = "examples/mode/pack:spaceman";

	protected String SndJump = "examples/mode/jump.mp3";
	protected String SndLand = "examples/mode/land.mp3";
	protected String SndExplode = "examples/mode/asplode.mp3";
	protected String SndExplode2 = "examples/mode/menu_hit_2.mp3";
	protected String SndHurt = "examples/mode/hurt.mp3";
	protected String SndJam = "examples/mode/jam.mp3";
	
	protected int _jumpPower;
	protected FlxGroup _bullets;
	protected int _aim;
	protected float _restart;
	protected FlxEmitter _gibs;

	private FlxSound _sfxJump;
	private FlxSound _sfxLand;
	private FlxSound _sfxExplode;
	private FlxSound _sfxExplode2;
	private FlxSound _sfxHurt;
	private FlxSound _sfxJam;
	
	

	//This is the player object class.  Most of the comments I would put in here
	//would be near duplicates of the Enemy class, so if you're confused at all
	//I'd recommend checking that out for some ideas!
	public Player(int X,int Y,FlxGroup Bullets,FlxEmitter Gibs)
	{
		super(X,Y);
		loadGraphic(ImgSpaceman,true,true,8);
		_restart = 0;

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

		//bullet stuff
		_bullets = Bullets;
		_gibs = Gibs;
		
		_sfxExplode = new FlxSound().loadEmbedded(SndExplode, false, false, FlxSound.SFX);
		_sfxExplode2 = new FlxSound().loadEmbedded(SndExplode2, false, false, FlxSound.SFX);
		_sfxHurt = new FlxSound().loadEmbedded(SndHurt, false, false, FlxSound.SFX);
		_sfxJam = new FlxSound().loadEmbedded(SndJam, false, false, FlxSound.SFX);
		_sfxJump = new FlxSound().loadEmbedded(SndJump, false, false, FlxSound.SFX);
		_sfxLand = new FlxSound().loadEmbedded(SndLand, false, false, FlxSound.SFX);
	}

	@Override
	public void destroy()
	{
		super.destroy();
		_bullets = null;
		_gibs = null;
	}

	@Override
	public void update()
	{
		//game restart timer
		if(!alive)
		{
			_restart += FlxG.elapsed;
			if(_restart > 2)
				FlxG.resetState();
			return;
		}

		//make a little noise if you just touched the floor
		if(justTouched(FLOOR) && (velocity.y > 50))
			_sfxLand.play(true);

		//MOVEMENT
		acceleration.x = 0;
		if(FlxG.keys.LEFT)
		{
			setFacing(LEFT);
			acceleration.x -= drag.x;
		}
		else if(FlxG.keys.RIGHT)
		{
			setFacing(RIGHT);
			acceleration.x += drag.x;
		}
		if(FlxG.keys.justPressed("X") && (int) velocity.y == 0)
		{
			velocity.y = -_jumpPower;
			_sfxJump.play(true);
		}

		//AIMING
		if(FlxG.keys.UP)
			_aim = UP;
		else if(FlxG.keys.DOWN && velocity.y != 0)
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
		if(FlxG.keys.justPressed("C"))
		{
			if(getFlickering())
				_sfxJam.play(true);
			else
			{
				getMidpoint(_point);
				((Bullet) _bullets.recycle(Bullet.class)).shoot(_point,_aim);
				if(_aim == DOWN)
					velocity.y -= 36;
			}
		}
	}

	@Override
	public void hurt(float Damage)
	{
		Damage = 0;
		if(getFlickering())
			return;
		_sfxHurt.play(true);
		flicker(1.3f);
		if(FlxG.score > 1000) FlxG.score -= 1000;
		if(velocity.x > 0)
			velocity.x = -maxVelocity.x;
		else
			velocity.x = maxVelocity.x;
		super.hurt(Damage);
	}

	@Override 
	public void kill()
	{
		if(!alive)
			return;
		setSolid(false);
		_sfxExplode.play(true);
		_sfxExplode2.play(true);
		super.kill();
		flicker(0);
		exists = true;
		visible = false;
		velocity.make();
		acceleration.make();
		FlxG.camera.shake(0.005f,0.35f);
		FlxG.camera.flash(0xffd8eba2,0.35f);
		if(_gibs != null)
		{
			_gibs.at(this);
			_gibs.start(true,5,0,50);
		}
	}
}