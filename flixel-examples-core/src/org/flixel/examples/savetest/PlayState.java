package org.flixel.examples.savetest;

import org.flixel.FlxG;
import org.flixel.FlxPoint;
import org.flixel.FlxSave;
import org.flixel.FlxState;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 *
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{

	@Override
	public void create()
	{
		FlxSave save = new FlxSave();
		if(!save.bind("savetest"))
			throw new GdxRuntimeException("bind fails");
		
		
		// WRITE
		
		// Integer
		save.data.put("integer", 1234);
		
		// Float
		save.data.put("float1", 1234567890f);
		save.data.put("float2", 1234567890.123f);
		
		// String
		save.data.put("string1", "flixel");
		save.data.put("string2", "flixel-gdx");
		save.data.put("string3", "flixel-gdx rocks! Awesome!");
		
		// Array
		Array<String> array1 = new Array<String>();
		array1.add("hello world");
		save.data.put("array1", array1);
		
		Array<FlxPoint> array2 = new Array<FlxPoint>();
		array2.add(new FlxPoint(120, 200));
		save.data.put("array2", array2);
		
		// Object
		FlxPoint point = new FlxPoint(300, 290);
		save.data.put("object1", point);
		
		// Object array, this won't work in GWT!
		FlxPoint[] points = new FlxPoint[1];
		points[0] = new FlxPoint(100, 110);
		save.data.put("object2", points);
		
		save.flush();
		
		
		// READ
		if(save.data.get("integer", Integer.class) != 1234) FlxG.log("int failed");
		if(save.data.get("float1", Float.class) != 1234567890f) FlxG.log("float1 failed");
		if(save.data.get("float2", Float.class) != 1234567890.123f) FlxG.log("float2 failed");
		if(!save.data.get("string1", String.class).equals("flixel")) FlxG.log("string1 failed");
		if(!save.data.get("string2", String.class).equals("flixel-gdx")) FlxG.log("string2 failed");
		if(!save.data.get("string3", String.class).equals("flixel-gdx rocks! Awesome!")) FlxG.log("string3 failed");
		if(!save.data.get("array1", Array.class).get(0).equals("hello world")) FlxG.log("array1 failed");
		
		@SuppressWarnings("unchecked")
		Array<FlxPoint> a2 = save.data.get("array2", Array.class);
		if(a2.get(0).x != 120f || a2.get(0).y != 200f) FlxG.log("array2 failed");
		
		FlxPoint p = save.data.get("object1", FlxPoint.class);
		if(p.x != 300f || p.y != 290f) FlxG.log("object1 failed");
		
		FlxPoint[] ps = save.data.get("object2", FlxPoint[].class);
		if(ps[0].x != 100f || ps[0].y != 110f) FlxG.log("object2 failed");
		
		
		// ERASE
		save.erase();
	}
}