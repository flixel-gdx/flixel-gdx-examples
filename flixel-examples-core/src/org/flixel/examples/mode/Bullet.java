package org.flixel.examples.mode;

import org.flixel.*;

public class Bullet extends FlxSprite
{
	private String ImgBullet = "examples/mode/pack:bullet";
	private String SndHit = "examples/mode/jump.mp3";
	private String SndShoot = "examples/mode/shoot.mp3";
	
	private FlxSound _sfxHit;
	private FlxSound _sfxShoot;
	
	public float speed;

	public Bullet()
	{
		super();
		loadGraphic(ImgBullet,true);
		width = 6;
		height = 6;
		offset.x = 1;
		offset.y = 1;

		addAnimation("up",new int[]{0});
		addAnimation("down",new int[]{1});
		addAnimation("left",new int[]{2});
		addAnimation("right",new int[]{3});
		addAnimation("poof",new int[]{4, 5, 6, 7}, 50, false);

		speed = 360;
		
		_sfxHit = new FlxSound().loadEmbedded(SndHit, false, false, FlxSound.SFX);
		_sfxShoot = new FlxSound().loadEmbedded(SndShoot, false, false, FlxSound.SFX);
	}

	@Override
	public void update()
	{
		if(!alive)
		{
			if(finished)
				exists = false;
		}
		else if(touching > NONE)
			kill();
	}

	@Override
	public void kill()
	{
		if(!alive)
			return;
		velocity.x = 0;
		velocity.y = 0;
		if(onScreen())
			_sfxHit.play(true);
		alive = false;
		setSolid(false);
		play("poof");
	}

	public void shoot(FlxPoint Location, int Aim)
	{
		_sfxShoot.play(true);
		super.reset(Location.x-width/2,Location.y-height/2);
		setSolid(true);
		switch(Aim)
		{
			case UP:
				play("up");
				velocity.y = -speed;
				break;
			case DOWN:
				play("down");
				velocity.y = speed;
				break;
			case LEFT:
				play("left");
				velocity.x = -speed;
				break;
			case RIGHT:
				play("right");
				velocity.x = speed;
				break;
			default:
				break;
		}
	}
}