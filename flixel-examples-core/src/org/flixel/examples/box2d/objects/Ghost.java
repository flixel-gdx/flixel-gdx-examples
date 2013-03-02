package org.flixel.examples.box2d.objects;

import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;

/**
 *
 * @author Ka Wing Chin
 */
public class Ghost extends B2FlxBox
{
	private final String ImgGhost = "examples/box2d/pack:ghost";
	public boolean sensor = true;
	private int _overlapCount;
	
	public Ghost(float x, float y, short categoryBits, short maskBits, short groupIndex, boolean sensor)
	{
		super(x, y, 64, 64);
		fixtureDef.filter.categoryBits = categoryBits;
		fixtureDef.filter.maskBits = maskBits;
		fixtureDef.filter.groupIndex = groupIndex;
		fixtureDef.isSensor = sensor;
		loadGraphic(ImgGhost);
		setDraggable(true);
		create();
		reportBeginContact = true;
		reportEndContact = true;
		reportPostSolve = true;
		reportPreSolve = true;
		_overlapCount = 0;
	}
	
	public void addOverlap()
	{
		_overlapCount++;		
	}
	
	public void removeOverlap()
	{
		_overlapCount--;
	}
	
	public boolean gotOverlaps()
	{		
		return _overlapCount > 0;
	}
	
}

