package org.flixel.examples.bomberplanet;

import org.flixel.*;
import org.flixel.event.IFlxCamera;

public class Player extends FlxSprite
{
	static public String ImgPlayer = "examples/bomberplanet/data/pack:player";
	static public String SndHurt = "examples/bomberplanet/data/hurt.mp3";

	public int target;
	public int moving;
	public float walkSpeed;
	public float restartTimeout;
	public float maxHealth;

	public int bombs;
	public int maxBombs;

	public boolean updated;

	public boolean launchpad;
	public boolean firedFlare;

	public Player()
	{
		super(119*16+8,115*16); //starting tile - may change!
		loadGraphic(ImgPlayer,true,true);

		int walkFR = 8;
		addAnimation("idle_up",new int[]{3},0,false);
		addAnimation("idle_down",new int[]{0},0,false);
		addAnimation("idle_side",new int[]{6},0,false);
		addAnimation("walk_up",new int[]{3,4,3,5},walkFR);
		addAnimation("walk_down",new int[]{0,1,0,2},walkFR);
		addAnimation("walk_side",new int[]{6,7,6,8},walkFR);
		addAnimation("dead",new int[]{9},0,false);

		moving = NONE;
		setFacing(DOWN);

		maxHealth = 2;
		health = maxHealth;
		maxBombs = 1;
		bombs = maxBombs;

		launchpad = false;
		firedFlare = false;

		updated = false;
	}

	@Override
	public void update()
	{
		if(!updated)
		{
			((PlayState)FlxG.getState()).updateHealthHUD();
			((PlayState)FlxG.getState()).updateBombHUD();
			updated = true;
		}

		if(!alive)
		{
			restartTimeout -= FlxG.elapsed;
			if(restartTimeout <= 0)
				FlxG.resetState();
			return;
		}

		//WALKIN AROUND AND SHIT
		walkSpeed = 64;
		if(getFlickering())
			walkSpeed *= 0.5f;
		else if(FlxG.keys.X)
			walkSpeed *= 2;
		if(touching > 0)
		{
			moving = NONE;
			velocity.x = velocity.y = 0;
		}
		if(moving == NONE)
			queryAll();
		switch(moving)
		{
			case LEFT:
				queryLeft();
				if(x <= target)
				{
					x = target;
					moving = NONE;
					velocity.x = 0;
				}
				else
				{
					velocity.x = -walkSpeed;
					setFacing(LEFT);
				}
				break;
			case RIGHT:
				queryRight();
				if(x >= target)
				{
					x = target;
					moving = NONE;
					velocity.x = 0;
				}
				else
				{
					velocity.x = walkSpeed;
					setFacing(RIGHT);
				}
				break;
			case UP:
				queryUp();
				if(y <= target)
				{
					y = target;
					moving = NONE;
					velocity.y = 0;
				}
				else
				{
					velocity.y = -walkSpeed;
					setFacing(UP);
				}
				break;
			case DOWN:
				queryDown();
				if(y >= target)
				{
					y = target;
					moving = NONE;
					velocity.y = 0;
				}
				else
				{
					velocity.y = walkSpeed;
					setFacing(DOWN);
				}
				break;
			default:
				velocity.x = velocity.y = 0;
				break;
		}

		//bombs!!
		if(FlxG.keys.justPressed("C"))
		{
			if(launchpad)
			{
				if(!firedFlare)
				{
					((PlayState)FlxG.getState()).add(new Flare(x,y));
					firedFlare = true;
					FlxG.fade(0xff000000,5,youWon);
				}
			}
			else if(bombs > 0)
			{
				FlxPoint bomb = new FlxPoint();
				bomb.x = x;
				bomb.y = y;

				if((moving == LEFT) || (moving == RIGHT))
					bomb.x = target;
				else if((moving == UP) || (moving == DOWN))
					bomb.y = target;

				if(getFacing() == LEFT)
					bomb.x -= 16;
				else if(getFacing() == RIGHT)
					bomb.x += 16;
				else if(getFacing() == UP)
					bomb.y -= 16;
				else if(getFacing() == DOWN)
					bomb.y += 16;

				bombsDec();
				((Bomb) ((PlayState)FlxG.getState()).bombs.recycle(Bomb.class)).reset(bomb.x,bomb.y);
			}
		}

		//animation
		String suffix;
		if(getFacing() == UP)
			suffix = "up";
		else if(getFacing() == DOWN)
			suffix = "down";
		else
			suffix = "side";
		if((velocity.x != 0) || (velocity.y != 0))
			play("walk_"+suffix);
		else
			play("idle_"+suffix);
	}

	public boolean queryLeft()
	{
		if(FlxG.keys.LEFT)
		{
			moving = LEFT;
			target = snap(x-1);
			return true;
		}
		return false;
	}

	public boolean queryRight()
	{
		if(FlxG.keys.RIGHT)
		{
			moving = RIGHT;
			target = snap(x+8);
			return true;
		}
		return false;
	}

	public boolean queryUp()
	{
		if(FlxG.keys.UP)
		{
			moving = UP;
			target = snap(y-1);
			return true;
		}
		return false;
	}

	public boolean queryDown()
	{
		if(FlxG.keys.DOWN)
		{
			moving = DOWN;
			target = snap(y+8);
			return true;
		}
		return false;
	}

	public boolean queryAll()
	{
		return queryLeft() || queryRight() || queryUp() || queryDown();
	}

	public int snap(float Value)
	{
		return (int)(Value/8) * 8;
	}

	@Override
	public void hurt(float Damage)
	{
		if(getFlickering())
			return;
		super.hurt(Damage);
		if(alive)
			flicker();
		((PlayState)FlxG.getState()).updateHealthHUD();
		FlxG.play(SndHurt,0.8f);
	}

	@Override
	public void kill()
	{
		super.kill();
		exists = true;
		visible = true;
		play("dead");
		setSolid(false);
		velocity.x = 0;
		velocity.y = 0;
		FlxG.music.stop();
		restartTimeout = 2;
	}

	public void bombsDec()
	{
		if(bombs > 0)
			bombs--;
		((PlayState)FlxG.getState()).updateBombHUD();
	}

	public void bombsInc()
	{
		if(bombs < maxBombs)
			bombs++;
		((PlayState)FlxG.getState()).updateBombHUD();
	}

	public IFlxCamera youWon = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new VictoryState());
		}
	};
}