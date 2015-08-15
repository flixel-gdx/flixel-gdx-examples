package org.flixel.examples.tiledmap2;

import org.flixel.FlxEmitter;
import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxTilemap;
import org.flixel.ui.FlxVirtualPad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class PlayState extends FlxState
{	
	private static String MAP = "examples/tiledmap2/map01.json";
	private static String ImgBG = "examples/tiledmap2/pack2:bg";
	private static String ImgGibs = "examples/tiledmap2/pack2:gibs";
	private static String ImgTiles = "examples/tiledmap2/pack2:tiles";

	private FlxTilemap _level;
	private FlxVirtualPad _pad;

	@Override
	public void create()
	{
		// Background
		FlxG.setBgColor(0xffacbcd7);

		_pad = new FlxVirtualPad(FlxVirtualPad.DPAD_FULL, FlxVirtualPad.A_B);
		
		FlxG.width = 320;
		// Objects that are placed in the very front.
		FlxSprite decoration = new FlxSprite(256, 159, ImgBG);
		decoration.moves = false;
		decoration.setSolid(false);
		add(decoration);
		add(new FlxText(32, 36, 96, "collision").setFormat(null, 16, 0xFF778ea1, "center"));
		add(new FlxText(32, 60, 96, "DEMO").setFormat(null, 24, 0xFF778ea1, "center"));

		
		JsonValue value = new JsonReader().parse(Gdx.files.internal(MAP));
		JsonValue layers = value.getChild("layers");
		
		// The tile data.
		int[] data = layers.get("data").asIntArray();
		
		// Go to the next layer which holds the objects.
		JsonValue objs = layers.next;		
		JsonValue objects = objs.get("objects");
		for(int i = 0; i < objects.size; i++)
		{
			String name = objects.get(i).getString("name");
			JsonValue jv = objects.get(i);
			if(name != null)
			{
				float x = jv.getFloat("x");
				float y = jv.getFloat("y");
				float width = jv.getInt("width");				
				float height = jv.getInt("height");
				if(name.equals("crate"))
					add(new Crate(x, y));
				else if(name.equals("elevator"))
					add(new Elevator(x, 80, height));
				else if(name.equals("pusher"))
					add(new Pusher(x, y, width));
				 if(name.equals("player"))
					add(new Player(x, y, _pad));
				else if(name.equals("dispenser"))
				{	
					
					JsonValue properties = objects.get(i).get("properties");
					float minVX = properties.getFloat("minvx");
					float minVY = properties.getFloat("minvy");
					float maxVX = properties.getFloat("maxvx");
					float maxVY = properties.getFloat("maxvy");
					int quantity = properties.getInt("quantity");
					
					// This is the thing that spews nuts and bolts
					FlxEmitter dispenser = new FlxEmitter(x, y);
					dispenser.setSize(8, 40);
					dispenser.setXSpeed(minVX, maxVX);
					dispenser.setYSpeed(minVY, maxVY);
					dispenser.gravity = properties.getFloat("gravity");
					dispenser.bounce = properties.getFloat("bounce");
					dispenser.makeParticles(ImgGibs, quantity, 16, true, 0.8f);
					dispenser.start(false, 10, 0.035f);
					add(dispenser);
				}
			}
		}
		
		// The rest of the map data is also in the file, but we did this manually for now.
		_level = new FlxTilemap();
		_level.loadMap(FlxTilemap.arrayToCSV(data, 40), ImgTiles, 8, 8, FlxTilemap.OFF, 1);
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
