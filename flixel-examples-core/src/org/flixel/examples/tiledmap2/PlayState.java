package org.flixel.examples.tiledmap2;

import org.flixel.FlxEmitter;
import org.flixel.FlxG;
import org.flixel.FlxPoint;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxTilemap;
import org.flixel.ui.FlxVirtualPad;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;

public class PlayState extends FlxState
{
	private static String MAP = "examples/tiledmap2/map01.tmx";
	private static String ImgBG = "examples/tiledmap2/pack:bg";
	private static String ImgGibs = "examples/tiledmap2/pack:gibs";
	private static String ImgTiles = "examples/tiledmap2/pack:tiles";

	public static TiledMap map;
	private FlxTilemap _level;
	private FlxVirtualPad _pad;

	@Override
	public void create()
	{
		TmxMapLoader loader = new TmxMapLoader();
		Parameters args = new Parameters();
		args.yUp = false;
		map = loader.load(MAP, args);		

		// Background
		FlxG.setBgColor(0xffacbcd7);

		FlxG.width = FlxG.camera.viewportWidth;

		FlxG.camera.scroll.x = -(FlxG.width - FlxG.camera.width) / 2;

		FlxG.camera.width = FlxG.width;
		_pad = new FlxVirtualPad(FlxVirtualPad.DPAD_FULL, FlxVirtualPad.A_B);
		_pad.setAll("scrollFactor", new FlxPoint(0, 0));
		FlxG.width = 320;
		// Objects that are placed in the very front.
		FlxSprite decoration = new FlxSprite(256, 159, ImgBG);
		decoration.moves = false;
		decoration.setSolid(false);
		add(decoration);
		add(new FlxText(32, 36, 96, "collision").setFormat(null, 16, 0xFF778ea1, "center"));
		add(new FlxText(32, 60, 96, "DEMO").setFormat(null, 24, 0xFF778ea1, "center"));

		MapLayers layers = map.getLayers();
		MapObjects objects = layers.get("objects").getObjects();
		RectangleMapObject rectObj;
		for(MapObject mapObject : objects)
		{
			rectObj = (RectangleMapObject) mapObject;
			
			// Draw sprites where objects occur
			String name = mapObject.getName();
			if(name != null)
			{
				float x = rectObj.getRectangle().x;
				float y = rectObj.getRectangle().y;
				float width = rectObj.getRectangle().width;				
				float height = rectObj.getRectangle().height;
				if(name.equals("crate"))
					add(new Crate(x, y));
				else if(name.equals("elevator"))
					add(new Elevator(x, 80, height));
				else if(name.equals("pusher"))
					add(new Pusher(x, y, width));
				else if(name.equals("player"))
					add(new Player(x, y, _pad));
				else if(name.equals("dispenser"))
				{
					float minVX = Float.parseFloat(rectObj.getProperties().get("minvx", String.class));
					float minVY = Float.parseFloat(rectObj.getProperties().get("minvy", String.class));
					float maxVX = Float.parseFloat(rectObj.getProperties().get("maxvx", String.class));
					float maxVY = Float.parseFloat(rectObj.getProperties().get("maxvy", String.class));
					int quantity = Integer.parseInt(rectObj.getProperties().get("quantity", String.class));
					
					// This is the thing that spews nuts and bolts
					FlxEmitter dispenser = new FlxEmitter(x, y);
					dispenser.setSize(8, 40);
					dispenser.setXSpeed(minVX, maxVX);
					dispenser.setYSpeed(minVY, maxVY);
					dispenser.gravity = Float.parseFloat(rectObj.getProperties().get("gravity", String.class));
					dispenser.bounce = Float.parseFloat(rectObj.getProperties().get("bounce", String.class));
					dispenser.makeParticles(ImgGibs, quantity, 16, true, 0.8f);
					dispenser.start(false, 10, 0.035f);
					add(dispenser);
				}
			}
		}
		
		_level = new FlxTilemap();		
		_level.loadMap(FlxTilemap.tiledmapToCSV(map, "map"), ImgTiles, 8, 8, FlxTilemap.OFF, 1);

		add(_level);
		add(_pad);
		_pad.setAlpha(0.5f);
	}

	@Override
	public void destroy()
	{
		super.destroy();
		_level = null;
		map.dispose();
	}

	@Override
	public void update()
	{
		super.update();
		FlxG.collide();
	}
}
