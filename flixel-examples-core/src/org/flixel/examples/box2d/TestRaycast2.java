package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flxbox2d.B2FlxB;
import org.flxbox2d.collision.shapes.B2FlxBox;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.ObjectMap;

import flash.display.Graphics;

/**
 *
 * @author Ka Wing Chin
 */
public class TestRaycast2 extends Test
{
	RayCastClosestCallback ccallback = new RayCastClosestCallback();
	Vector2 point1 = new Vector2();
	Vector2 point2 = new Vector2();
	Vector2 d = new Vector2();
	Vector2 pooledHead = new Vector2();
	float angle;
	
	@Override
	public void create()
	{
		super.create();
		B2FlxB.world.setGravity(new Vector2(0, 0));
		
		// Fixture definition
		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = .3f;
		fd.restitution = .1f;
				
		// Spawn in a bunch of crap
		B2FlxBox box;
		int i = 0;
		for(i = 0; i < 5; i++)
		{
			add(box = new B2FlxBox(FlxG.random() * 400 + 100, FlxG.random() * 150 + 50, 
							FlxG.random() * 10 + 20, FlxG.random() * 10 + 20)
					.setFixtureDef(fd)
					.setDraggable(true)
					.create()
				);
			box.userData.put("data", 256);
		}
		
		angle = 0;
	}
	
	@Override
	public void update()
	{		
		float L = 11;
		point1.set(0, 10);
		d.set(L * MathUtils.cos(angle), L * MathUtils.sin(angle));
		point2.set(point1);
		point2.add(d);
		
		B2FlxB.world.rayCast(ccallback, point1, point2);
		FlxG.log(ccallback.hit);
		
		super.update();
	}
	
	@Override
	public void draw()
	{
		super.draw();
		Graphics graphics = FlxG.flashGfx;
		graphics.lineStyle(1.f, 0xFF0000, 1.f);
		graphics.moveTo(point1.x * 32, point1.y * 32);
		graphics.lineTo(point2.x * 60, point2.y * 60);
	}
	
}

class RayCastClosestCallback implements RayCastCallback
{
	public boolean hit;
	Vector2 point;
	Vector2 normal;

	@SuppressWarnings("unchecked")
	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction)
	{
		hit = false;
		Body body = fixture.getBody();
		ObjectMap<String, Object> userData = (ObjectMap<String, Object>) body.getUserData();
		if(userData != null)
		{
			int index = (Integer) userData.get("data");
			if(index == 0)
			{
				// filter
				return -1f;
			}
		}
		hit = true;
		this.point = point;
		this.normal = normal;
		return fraction;
	}
	
}

