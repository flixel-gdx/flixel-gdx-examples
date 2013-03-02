package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.FlxU;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxPolygon;
import org.flixel.plugin.flxbox2d.common.B2FlxV2;
import org.flixel.plugin.flxbox2d.dynamics.joints.B2FlxRevoluteJoint;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 * @author Ka Wing Chin
 */
public class TestBridge extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("Bridge");
		
		// Bridge
		final int numPlanks = 10;
		B2FlxBox prevBody = null;
		B2FlxBox plank = null;
		int i;
		for(i = 0; i < numPlanks; i++)
		{
			plank = new B2FlxBox(76 + 22 + 44 * i, 245, 48, 10)
				.setDensity(20)
				.setFriction(.2f)
				.setDraggable(true)
				.create();
			add(plank);
			
			add(new B2FlxRevoluteJoint(prevBody, plank)
				.setLowerAngle(-15f)
				.setUpperAngle(15f)
				.setEnableLimit(true)
				.setAnchorA(new B2FlxV2(100 + 44 * i, 250))
				.setShowLine(true)
				.create());
			
			prevBody = plank;
		}
		
		// Nail the last plank
		new B2FlxRevoluteJoint(null, plank)
			.setAnchorA(new B2FlxV2(100 + 44 * numPlanks, 250))
			.create();
		
		
		// Fixture definition
		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = .3f;
		fd.restitution = .1f;
		
		// Spawn in a bunch of crap
		for(i = 0; i < 5; i++)
		{
			add(new B2FlxBox(FlxG.random() * 400 + 100, FlxG.random() * 150 + 50, 
					FlxG.random() * 10 + 20, FlxG.random() * 10 + 20)
				.setFixtureDef(fd)
				.setDraggable(true)
				.create()
			);					
		}
		
		for(i = 0; i < 5; i++)
		{
			add(new B2FlxCircle(FlxG.random() * 400 + 100, FlxG.random() * 150 + 50, 
					FlxG.random() * 5 +10)
				.setFixtureDef(fd)
				.setDraggable(true)
				.create()
			);
		}
		
		float r;
		float[][] vertices = null;
		for(i = 0; i < 15; i++)
		{
			if(FlxG.random() > .66f)
			{
				vertices = new float[4][2]; 
				vertices[0] = new float[]{(-10 - FlxG.random() * 10), ( 10 + FlxG.random() * 10)};
				vertices[1] = new float[]{( -5 - FlxG.random() * 10), (-10 - FlxG.random() * 10)};
				vertices[2] = new float[]{(  5 + FlxG.random() * 10), (-10 - FlxG.random() * 10)};
				vertices[3] = new float[]{( 10 + FlxG.random() * 10), ( 10 + FlxG.random() * 10)};
			}
			else if(FlxG.random() > .55f)
			{
				vertices = new float[5][2];
				vertices[0] = new float[]{0, (10 + FlxG.random() * 10)};
				vertices[2] = new float[]{-5 - FlxG.random() * 10, -10 - FlxG.random() * 10};
				vertices[3] = new float[]{5 + FlxG.random() * 10, -10 - FlxG.random() * 10};
				vertices[1] = new float[]{vertices[0][0] + vertices[2][0], vertices[0][1] + vertices[2][1]};
				r = FlxG.random() / 2f + 0.8f;
				vertices[1][0] *= r;
				vertices[1][1] *= r;
				vertices[4] = new float[]{vertices[3][0] + vertices[0][0], vertices[3][1] + vertices[0][1]};
				r = FlxG.random() / 2f + 0.8f;
				vertices[4][0] *= r;
				vertices[4][1] *= r;
			}
			else
			{
				vertices = new float[3][2];
				vertices[0] = new float[]{0, (10 + FlxG.random() * 10)};
				vertices[1] = new float[]{(-5 - FlxG.random() * 10), (-10 - FlxG.random() * 10)};
				vertices[2] = new float[]{( 5 + FlxG.random() * 10), (-10 - FlxG.random() * 10)};
			}
			
			add(new B2FlxPolygon(FlxG.random() * 400 + 100, FlxG.random() * 150 + 50, new float[][][]{vertices})
					.setFixtureDef(fd)
					.setAngle(FlxG.random() * FlxU.PI)
					.setDraggable(true)
					.create()
				);
		}
		
	}
}

