package org.flixel.examples.controller;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxTilemap;
import org.flixel.examples.controller.mappings.OuyaController;
import org.flixel.examples.controller.mappings.PS2Adaptor;
import org.flixel.examples.controller.mappings.PSPFuSa;
import org.flixel.examples.controller.mappings.Xbox360Controller;
import org.flixel.plugin.GamepadManager;
import org.flixel.system.input.gamepad.Gamepad;
import org.flixel.system.input.gamepad.GamepadMapping;

/**
 * This demo demonstrates how use the controller plugin.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	private String ImgMap = "examples/controller/map.png";
	private String ImgTiles = "examples/controller/pack:tiles";
	
	private FlxTilemap level;	
	private Gamepad gamepad1;
	private Gamepad gamepad2;
	private Gamepad gamepad3;
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
		FlxG.setBgColor(0xff4B6A9E);
		
		// Add the GamepadManager plugin.
		FlxG.addPlugin(new GamepadManager());
		
		// Create some mappings, ID is placed between brackets.
		// PSP (FuSa GamePad).
		GamepadMapping psp = new PSPFuSa();		
		// Dual Shock 2 + Logic3 Adapter (PS(R) Gamepad Adaptor).
		GamepadMapping ds2 = new PS2Adaptor();
		// Xbox 360 Controller (ID varies).
		GamepadMapping xbox360 = new Xbox360Controller();		
		// Ouya Controller (Ouya Controller)
		GamepadMapping ouya = new OuyaController();
		
		// Add the mappings.
		GamepadManager.addMapping(psp);
		GamepadManager.addMapping(ds2);
		GamepadManager.addMapping(ouya);
		GamepadManager.addMapping(xbox360);
		
		// Create gamepads. You can add as many as you like.
		GamepadManager.addGamepad(gamepad1 = new Gamepad()); // 1st controller
		GamepadManager.addGamepad(gamepad2 = new Gamepad()); // 2nd controller
		GamepadManager.addGamepad(gamepad3 = new Gamepad()); // 3rd controller
				
		add(new FlxText(20,20,200,"requires gamepad!").setFormat(null,16,0xFF778ea1,"center"));
		
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
		FlxG.collide(player1, bullet2);
		FlxG.collide(player1, bullet3);
		FlxG.collide(player2, bullet1);
		FlxG.collide(player2, bullet3);
		FlxG.collide(player3, bullet1);
		FlxG.collide(player3, bullet2);
		FlxG.collide(players, level);
		FlxG.collide(bullets, level);
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.getPlugin(GamepadManager.class).destroy();
		FlxG.removePluginType(GamepadManager.class);
	}
}

