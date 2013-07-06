package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.B2FlxB;
//import org.flixel.plugin.flxbox2d.collision.RayCastOutput;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.common.B2FlxV2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * 
 * @author Ka Wing Chin
 */
public class TestRaycast extends Test
{
	private B2FlxBox box;
	private RayCastAnyCallback callback;

	@Override
	public void create()
	{
		super.create();
		title.setText("Raycast");

		B2FlxB.world.setGravity(new Vector2(0, 0));

		FixtureDef fd = new FixtureDef();
		fd.density = 4;
		fd.friction = .4f;
		fd.restitution = .3f;

		box = (B2FlxBox) new B2FlxBox(10, 150, 60, 8).setFixtureDef(fd).setAngle(180).setDraggable(true).create();
		box.userData.put("data", 256);
		add(box);
		
		//add(new B2FlxCircle(100, 100, 30).setFixtureDef(fd).setDraggable(true).create());

		// B2FlxB.world.rayCast(cb, new Vector2(0, 0), new Vector2(0, 0));
		callback = new RayCastAnyCallback();
	}

	Vector2 point1 = new Vector2();
	Vector2 point2 = new Vector2();
	Vector2 d = new Vector2();
	Vector2 pooledHead = new Vector2();

	@Override
	public void update()
	{
		float L = 11;
		point1.set(0.0f, 10.0f);
		d.set(L * MathUtils.cos(0), L * MathUtils.sin(0));
		point2.set(point1);
		point2.add(d);
		
		// FlxG.log(point1);
//		FlxG.log(point2);
		callback.hit = false;
		
		
//		FlxG.log(callback.m_hit);
		// TODO: fix this part
		
		super.update();
	}

	@Override
	public void draw()
	{
		super.draw();
		Vector2 p1 = box.body.getWorldPoint(new B2FlxV2(30.1f, 0));
		Vector2 p2 = box.body.getWorldPoint(new B2FlxV2(130.1f, 0));
		
		B2FlxB.world.rayCast(callback, point1, point2);
		Fixture f = callback.fixture;		
		float lambda = 1;//callback.fraction;
		if(f != null)
		{
//			RayCastInput input = new RayCastInput(p1, p2);
//			RayCastOutput output = new RayCastOutput();
//			f.rayCast(output, input); //TODO: ray cast implementation is not complete by libGDX.
//			lambda = output.fraction;
		}
		
		FlxG.log(p1.x + " :  " + p1.y + " == " + p2.x + " :  " + p2.y);
		
		ShapeRenderer laser = FlxG.flashGfx.getShapeRenderer();
		laser.end();
		laser.begin(ShapeType.Line);
		laser.setColor(Color.RED);
		laser.line(p1.x * 32f, p1.y * 32f, 
				(p2.x * lambda + (1 - lambda) * p1.x) * 32f, 
				(p2.x * lambda + (1 - lambda) * p1.x) * 32f);
		laser.end();
	}
	
	@Override
	public void drawDebug()
	{
		// TODO Auto-generated method stub
		super.drawDebug();
	}
}

class RayCastAnyCallback implements RayCastCallback
{

	public RayCastAnyCallback()
	{
		hit = false;
	}

	public Fixture fixture;
	public boolean hit;
	public Vector2 point;
	public Vector2 normal;
	public float fraction;

	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction)
	{
		hit = false;
		point = null;
		normal = null;
		fraction = 0;
		
		Body body = fixture.getBody();
		@SuppressWarnings("unchecked")
		ObjectMap<String, Object> userData = (ObjectMap<String, Object>) body.getUserData();
		if(userData != null)
		{
			if((Integer) userData.get("data") != null)
			{
				int index = (Integer) userData.get("data");
				if(index == 0)
				{
					// filter
					return -1f;
				}				
			}
		}
		this.fixture = fixture;
		hit = true;
		this.point = point;
		this.normal = normal;
		this.fraction = fraction;
		return 0f;
	}
};