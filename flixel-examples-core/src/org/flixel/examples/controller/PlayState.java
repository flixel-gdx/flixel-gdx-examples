package org.flixel.examples.controller;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxObject;
import org.flixel.FlxTilemap;
import org.flixel.event.IFlxCollision;

/**
 * This demo demonstrates how use the controller plugin.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends Test
{
	private String ImgMap = "examples/controller/map.png";
	private String ImgTiles = "examples/controller/pack:tiles";
	
	private FlxTilemap level;
	private Player player1;
	private Player player2;
	private Player player3;
	private FlxGroup bullet1;
	private FlxGroup bullet2;
	private FlxGroup bullet3;
	
	private FlxGroup players;
	private FlxGroup bullets;
	

	@Override
	public void create()
	{
		super.create();
		FlxG.setBgColor(0xff4B6A9E);
				
		add(bullets = new FlxGroup());
		bullets.add(bullet1 = new FlxGroup());
		bullets.add(bullet2 = new FlxGroup());
		bullets.add(bullet3 = new FlxGroup());
		// Hook the gamepads to players.
		add(players = new FlxGroup());
		players.add(player1 = new Player(20, 60, 0xFFFFFF00, bullet1, gamepad1));
		players.add(player2 = new Player(20, 200, 0xFFFF0000, bullet2, gamepad2));
		players.add(player3 = new Player(290, 60, 0xFF00FFFF, bullet3, gamepad3));
		
		//Basic level structure
		level = new FlxTilemap();
		level.loadMap(FlxTilemap.imageToCSV(ImgMap,false,2),ImgTiles,0,0,FlxTilemap.ALT);
		level.follow();
		add(level);		
	}

	@Override
	public void update()
	{
		super.update();
		FlxG.collide(players);
		FlxG.collide(bullets);
		FlxG.collide(player1, bullet2, hit);
		FlxG.collide(player1, bullet3, hit);
		FlxG.collide(player2, bullet1, hit);
		FlxG.collide(player2, bullet3, hit);
		FlxG.collide(player3, bullet1, hit);
		FlxG.collide(player3, bullet2, hit);
		FlxG.collide(players, level);
		FlxG.collide(bullets, level);
	}
	

	private IFlxCollision hit = new IFlxCollision()
	{		
		@Override
		public void callback(FlxObject Object1, FlxObject Object2)
		{
			Object1.hurt(0);
		}
	};
	
	@Override
	public void destroy()
	{
		super.destroy();
		level = null;
		player1 = null;
		player2 = null;
		player3 = null;
		players = null;
		bullets = null;
	}
}

