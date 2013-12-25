package org.flixel.examples.box2d;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxText;
import org.flixel.event.IFlxButton;
import org.flxbox2d.B2FlxB;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.collision.shapes.B2FlxCircle;
import org.flxbox2d.collision.shapes.B2FlxPolygon;
import org.flxbox2d.controllers.B2GravityController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 * @author Ka Wing Chin
 */
public class TestGravity extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("Gravity");
		info.setText("Circles aren't affected by the gravity changes.");
		B2FlxB.setGravity(0, 0);
		
		final Vector2 gravity = new Vector2(0, 0);
		final B2GravityController gc = new B2GravityController(gravity);
		B2FlxB.addController(gc);
		
		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = .3f;
		fd.restitution = .1f;
		
		int i;
		for(i = 0; i < 5; i++)
		{
			add(new B2FlxBox(FlxG.random() * 400 + 100, FlxG.random() * 150 + 50, 
					FlxG.random() * 10 + 20, FlxG.random() * 10 + 20)
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
					.setAngle((float) (FlxG.random() * Math.PI))
					.setDraggable(true)
					.create()
				);
			
			
			int length = B2FlxB.world.getBodyCount();
			B2FlxB.getBodies();
			for(i = 0; i < length; i++)
			{
				gc.addBody(B2FlxB.bodies.get(i));
			}
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
		
		final FlxText text = new FlxText(50, 185, 80, "0").setFormat(null, 16, 0xFFFFFF, "center");
		text.ignoreDrawDebug = true;
		add(text);
		
		FlxButton button = new FlxButton(50, 160, "INCREASE", new IFlxButton()
		{			
			@Override
			public void callback()
			{
				gravity.y += 1;
				gc.setGravity(0, gravity.y);
				text.setText(Float.toString(gravity.y));
			}
		});
		button.ignoreDrawDebug = true;
		add(button);
		button = new FlxButton(50, 210, "DECREASE", new IFlxButton()
		{			
			@Override
			public void callback()
			{
				gravity.y -= 1f;
				gc.setGravity(0, gravity.y);
				text.setText(Float.toString(gravity.y));
			}
		});
		button.ignoreDrawDebug = true;
		add(button);		
	}
}

