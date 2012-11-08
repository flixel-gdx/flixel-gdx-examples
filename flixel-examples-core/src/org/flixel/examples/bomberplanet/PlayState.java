package org.flixel.examples.bomberplanet;

import org.flixel.*;
import org.flixel.event.IFlxObject;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

public class PlayState extends FlxState
{
	static public String ImgMap = "examples/bomberplanet/data/pack:map";
	static public String ImgTiles = "examples/bomberplanet/data/pack:tiles";
	static public String ImgFootsteps = "examples/bomberplanet/data/pack:footsteps";
	static public String ImgBlockBits = "examples/bomberplanet/data/pack:blockbits";
	static public String SndMusic = "examples/bomberplanet/data/music.mp3";
	static public String ImgLaunchpad ="examples/bomberplanet/data/pack:launchpad";
	static public String ImgShipwreck = "examples/bomberplanet/data/pack:shipwreck"; 

	public FlxTilemap map;
	public Player player;
	public FlxGroup bombs;
	public FlxEmitter footsteps;
	public CameraHelper cameraHelper;
	public FlxGroup blocks;
	public FlxGroup enemies;

	public FlxGroup solidStuff;
	public FlxGroup movingStuff;

	public FlxGroup vulnerable;
	public FlxGroup hitboxes;

	public FlxGroup poofs;
	public FlxEmitter blockbits;

	public FlxGroup healthBoxes;
	public FlxGroup bombBoxes;

	public FlxGroup powerups;

	public FlxSprite launchpad;
	public boolean foundLaunchpad;

	@Override
	public void create()
	{
		FlxG.setBgColor(0xff24323f);

		player = new Player();

		//Processing the map data to get trap locations before making a simple collision/pathfinding hull		
		int emptyColor = 0xff000000;
		int blockColor = 0xff3c565c;
		int healthColor = 0xff24323f;
		int enemyColor = 0xffffffff;
		FlxSprite mapSprite = new FlxSprite(0,0,ImgMap);
		Array<FlxPoint> blockLocations = mapSprite.replaceColor(blockColor,emptyColor,true);
		blocks = new FlxGroup();
		addBlocks(blockLocations);
		Array<FlxPoint> enemyLocations = mapSprite.replaceColor(enemyColor,emptyColor,true);
		enemies = new FlxGroup();
		addEnemies(enemyLocations);
		Array<FlxPoint> healthLocations = mapSprite.replaceColor(healthColor,emptyColor,true);
		powerups = new FlxGroup();
		addHealthUps(healthLocations);
		
		//Create a color map of the different world zones
		int clrStart = 0xa7702d;
		int clrForest = 0x529023;
		int clrGrass = 0xa6cd33;
		int clrDesert = 0xea903e;
		int clrBeach = 0xf7e176;
		int clrRed = 0xbe3241;
		int clrIce = 0x3ea5f2;
		int clrPink = 0xdf7a92;
		int clrNight = 0x00648c;
		int clrMountain = 0x574a38;
		int clrPeak = 0xa1a1a1;
		int clrWater = 0xb3dbee;
		IntArray colorMap = new IntArray(new int[]{	0, clrStart, clrForest, clrGrass, clrDesert, clrBeach, clrRed,
										clrIce, clrPink, clrNight, clrMountain, clrPeak, clrWater });
		
		//Then load the map auto-indexed to our tile map based on those colors
		map = new FlxTilemap().loadMap(FlxTilemap.bitmapToCSV(mapSprite.getPixels(),false,1,colorMap),ImgTiles);
		add(map);

		FlxSprite ship = new FlxSprite(119*16,112*16,ImgShipwreck);
		ship.immovable = true;
		add(ship);

		footsteps = new FlxEmitter();
		footsteps.makeParticles(ImgFootsteps,50,0,true,0);
		footsteps.setRotation();
		footsteps.setXSpeed(0);
		footsteps.setYSpeed(0);
		footsteps.gravity = 0;
		footsteps.start(false,0,0.15f);
		add(footsteps);

		foundLaunchpad = false;
		launchpad = new FlxSprite(118*16,27*16,ImgLaunchpad);
		add(launchpad);

		add(blocks);

		add(enemies);

		bombBoxes = new FlxGroup();
		healthBoxes = new FlxGroup();

		add(player);

		add(powerups);

		bombs = new FlxGroup();
		add(bombs);

		solidStuff = new FlxGroup();
		solidStuff.add(blocks);
		solidStuff.add(bombs);
		solidStuff.add(map);

		movingStuff = new FlxGroup();
		movingStuff.add(player);
		movingStuff.add(enemies);

		vulnerable = new FlxGroup();
		vulnerable.add(blocks);
		vulnerable.add(player);
		vulnerable.add(enemies);
		vulnerable.add(bombs);

		hitboxes = new FlxGroup();
		add(hitboxes);

		blockbits = new FlxEmitter();
		blockbits.makeParticles(ImgBlockBits,40,64,true,0);
		blockbits.setRotation(-720,720);
		blockbits.setYSpeed(-300,-150);
		blockbits.setXSpeed(-200,200);
		blockbits.gravity = 800;
		add(blockbits);

		poofs = new FlxGroup();
		add(poofs);

		//drawn on top since its the hud
		add(healthBoxes);
		add(bombBoxes);

		//camera helper figures out when and where to scroll but shouldn't be added to state
		cameraHelper = new CameraHelper(player);
		FlxG.camera.follow(cameraHelper);

		FlxG.playMusic(SndMusic,0.35f);
		FlxG.flash(0xff000000,1);
	}

	@Override
	public void update()
	{
		//DEBUG: CTRL+SHIFT+E erases the local saved data & restarts the game
		if(FlxG.keys.CONTROL && FlxG.keys.SHIFT && FlxG.keys.E)
		{
			MenuState.eraseData();
			FlxG.resetState();
		}

		if(cameraHelper.pathSpeed == 0)
		{
			FlxG.worldBounds.x = FlxG.camera.scroll.x-10;
			FlxG.worldBounds.y = FlxG.camera.scroll.y-10;

			super.update();

			footsteps.at(player);
			footsteps.y += 4;

			FlxG.collide(solidStuff,movingStuff);
			if(hitboxes.countLiving() > 0)
				FlxG.overlap(vulnerable,hitboxes,null,onHitbox);
			FlxG.overlap(enemies,player,null,onEnemyPlayer);
			FlxG.overlap(powerups,player,null,onPowerup);

			if(player.overlaps(launchpad))
			{
				if(!foundLaunchpad)
				{
					FlxG.music.fadeOut(3);
					foundLaunchpad = true;
				}
				player.launchpad = true;
			}
			else
				player.launchpad = false;
		}
		cameraHelper.update();
	}

	public void addBlocks(Array<FlxPoint> Locations)
	{
		int l = Locations.size;
		while(l-- > 0)
			blocks.add(new Block(Locations.get(l).x*16,Locations.get(l).y*16));
	}

	public void addEnemies(Array<FlxPoint> Locations)
	{
		int l = Locations.size;
		while(l-- > 0)
			enemies.add(new Enemy(Locations.get(l).x*16,Locations.get(l).y*16));
	}

	public void addHealthUps(Array<FlxPoint> Locations)
	{
		Powerup p;
		int l = Locations.size;

		if(FlxG.levels.size <= 0) //no in-memory save data at the moment
		{
			int i;
			for(i = 0; i < l; i++)
				FlxG.levels.add(false);

			//check the local shared session to see if there's any data
			FlxSave save = new FlxSave();
			if(save.bind("escape"))
			{
				if(save.data.get("health", boolean[].class) == null)
				{
					//no data found, so create new data
					boolean[] health = new boolean[l];
					for(i = 0; i < l; i++)
						health[i] = false;
					save.data.put("health", health);
				}
				else
				{
					//a ha!  data found - update our local copy
					boolean[] health = save.data.get("health", boolean[].class);
					for(i = 0; i < l; i++)
						FlxG.levels.set(i, health[i]);
				}
				save.close();
			}
		}

		while(l-- > 0)
		{
			if((Boolean) FlxG.levels.get(l))
			{
				//copy pasta from onPowerup()
				player.maxHealth += 2;
				player.health = player.maxHealth;
			}
			else
			{
				p = new Powerup();
				p.resetPowerup(Locations.get(l).x*16,Locations.get(l).y*16,1,l);
				powerups.add(p);
			}
		}
	}

	public IFlxObject onHitbox = new IFlxObject()
	{	
		@Override
		public boolean callback(FlxObject A,FlxObject B)
		{
			if(A instanceof Hitbox)
				B.hurt(1);
			else
				A.hurt(1);
			
			return false;
		}
	};

	IFlxObject onEnemyPlayer = new IFlxObject()
	{
		@Override
		public boolean callback(FlxObject A,FlxObject B)
		{
			if(A instanceof Player)
				A.hurt(1);
			else
				B.hurt(1);
			
			return false;
		}
	};
	
	IFlxObject onPowerup = new IFlxObject()
	{
		@Override
		public boolean callback(FlxObject A,FlxObject B)
		{
			Powerup powerup;
			if(A instanceof Powerup)
				powerup = (Powerup) A;
			else
				powerup = (Powerup) B;
			if((powerup.getFrame() == 0) && player.maxBombs < 5)
			{
				player.maxBombs++;
				player.bombs = player.maxBombs;
				updateBombHUD();
			}
			else if(powerup.getFrame() == 1)
			{
				player.maxHealth += 2;
				player.health = player.maxHealth;
				updateHealthHUD();
			}
			else if(powerup.getFrame() == 2)
			{
				player.health++;
				if(player.health > player.maxHealth)
					player.health = player.maxHealth;
				updateHealthHUD();
			}
			powerup.kill();
			
			return false;
		}
	};

	public void updateHealthHUD()
	{
		float Health = player.health;
		float MaxHealth = player.maxHealth;

		int numBoxes = (int) FlxU.ceil(MaxHealth*0.5f);
		while(healthBoxes.length < numBoxes)
			healthBoxes.add(new HealthBox());

		int i = 0;
		int l = healthBoxes.length;
		HealthBox healthbox;
		while(i < l)
		{
			healthbox = (HealthBox) healthBoxes.members.get(i++);
			healthbox.setFrame((int) ((Health>2)?2:((Health<0)?0:Health)));
			Health -= 2;
			healthbox.x = FlxG.width - (l+1-i)*12 - 4;
		}
	}

	public void updateBombHUD()
	{
		int Bombs = player.bombs;
		int MaxBombs = player.maxBombs;

		while(bombBoxes.length < MaxBombs)
			bombBoxes.add(new BombBox());

		int i = 0;
		int l = bombBoxes.length;
		BombBox bombbox;
		while(i < l)
		{
			bombbox = (BombBox) bombBoxes.members.get(i++);
			bombbox.setFrame((Bombs>1)?1:((Bombs<0)?0:Bombs));
			Bombs--;
			bombbox.x = (i-1)*12;
		}
	}

	public void spawnPowerup(float X,float Y)
	{
		//0 = bomb up
		//1 = health up
		//2 = health
		int type;
		if(FlxG.random() < 0.15f)
			type = 0;
		else
			type = 2;
		((Powerup) powerups.recycle(Powerup.class)).resetPowerup(X,Y,type);
	}
}