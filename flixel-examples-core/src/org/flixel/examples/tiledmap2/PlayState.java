package org.flixel.examples.tiledmap2;

import org.flixel.FlxEmitter;
import org.flixel.FlxG;
import org.flixel.FlxGamePad;
import org.flixel.FlxPoint;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxTilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObject;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObjectGroup;

public class PlayState extends FlxState
{
	public static TiledMap map = TiledLoader.createMap(Gdx.files.internal("examples/tiledmap2/map01.tmx"));
	private static String ImgBG = "examples/tiledmap2/pack:bg";
	private static String ImgGibs = "examples/tiledmap2/pack:gibs";
	private static String ImgTiles = "examples/tiledmap2/pack:tiles";
	
	private FlxTilemap _level;
	private FlxGamePad _pad;
	
	@Override
	public void create()
	{
		//Background
		FlxG.setBgColor(0xffacbcd7);
		
		FlxG.width = FlxG.camera.viewportWidth;
		
		FlxG.camera.scroll.x = -(FlxG.width - FlxG.camera.width) / 2;
		
		FlxG.camera.width = FlxG.width;
		_pad = new FlxGamePad(FlxGamePad.FULL, FlxGamePad.A_B);
		_pad.setAll("scrollFactor", new FlxPoint(0,0));
		FlxG.width = 320;
		// Objects that are placed in the very front.
		FlxSprite decoration = new FlxSprite(256,159,ImgBG);
		decoration.moves = false;
		decoration.setSolid(false);
		add(decoration);
		add(new FlxText(32,36,96,"collision").setFormat(null,16,0xFF778ea1,"center"));
		add(new FlxText(32,60,96,"DEMO").setFormat(null,24,0xFF778ea1,"center"));
		
		for(TiledObjectGroup group : map.objectGroups)
		{
			for(TiledObject object : group.objects)
			{
				// Draw sprites where objects occur
				
				String name = object.name;
				if(name != null)
				{
					if(name.equals("crate"))
						add(new Crate(object.x,object.y));
					else if(name.equals("elevator"))
						add(new Elevator(object.x,object.y,object.height));
					else if(name.equals("pusher"))
						add(new Pusher(object.x,object.y,object.width));
					else if(name.equals("player"))
						add(new Player(object.x,object.y,_pad));
				}				
			}
		}
		
		//This is the thing that spews nuts and bolts
		FlxEmitter dispenser = new FlxEmitter(32,32,200);
		dispenser.setSize(8,40);
		dispenser.setXSpeed(100,240);
		dispenser.setYSpeed(-50,50);
		dispenser.gravity = 300;
		dispenser.bounce = 0.3f;
		dispenser.makeParticles(ImgGibs,100,16,true,0.8f);
		dispenser.start(false,10,0.035f);
		add(dispenser);
		
		_level = new FlxTilemap();
		_level.loadMap(FlxTilemap.tiledmapToCSV(map, 0), ImgTiles, 8, 8, FlxTilemap.OFF, 1);
		
		add(_level);
		add(_pad);
		_pad.setAlpha(0.5f);	
	}
	
	@Override
	public void destroy()
	{		
		super.destroy();
		_level = null;
	}
	
	@Override
	public void update()
	{
		super.update();
		FlxG.collide();
	}
}
