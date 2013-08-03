package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.plugin.flxbox2d.B2FlxB;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxPolygon;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxSprite;
import org.flixel.plugin.flxbox2d.controllers.B2BuoyancyController;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 * @author Ka Wing Chin
 */
public class TestBuoyancy extends Test
{	
	@Override
	public void create()
	{
		super.create();
		title.setText("Buoyancy");
		
		B2BuoyancyController bc = new B2BuoyancyController();
		bc.mSurfaceNormal.set(0, -1);
		bc.mSurfaceHeight = -200 / B2FlxB.RATIO;
		bc.mFluidDensity = 2;
		bc.mLinearDrag = 5;
		bc.mAngularDrag = 2;
		bc.mGravity.set(0, 9.8f);
		B2FlxB.addController(bc);
		
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
					.setAngle((float) (FlxG.random() * Math.PI))
					.setDraggable(true)
					.create()
				);
		}
		
		
		// Add some exciting bath toys
		add(new B2FlxBox(10, 290, 80, 20)
			.setDensity(3)
			.setDraggable(true)
			.create());
		
		B2FlxSprite toy = new B2FlxSprite(300, 300)
			.setDraggable(true)
			.create();
		add(toy);
		
		fd = new FixtureDef();
		fd.density = 2;
		B2FlxCircle circ = new B2FlxCircle(30, 120, 7).setFixtureDef(fd);
		circ.setShapePosition(30, 0);
		toy.createFixture(circ.fixtureDef);
		
		circ.setShapePosition(-30, 0);
		toy.createFixture(circ.fixtureDef);
		
		circ.setShapePosition(0, 30);
		toy.createFixture(circ.fixtureDef);
		
		circ.setShapePosition(0, -30);
		toy.createFixture(circ.fixtureDef);
		
		circ.disposeShape();
		
		toy.createFixture(new B2FlxBox(0, 0, 60, 4).setFixtureDef(fd));
		toy.createFixture(new B2FlxBox(0, 0, 4, 60).setFixtureDef(fd));
		
		
		int length = B2FlxB.world.getBodyCount();
		B2FlxB.getBodies();
		for(i = 0; i < length; i++)
		{
			bc.addBody(B2FlxB.bodies.get(i));
		}
		
		// Draw water line
		FlxSprite water = new FlxSprite(0, 200);
		water.makeGraphic(FlxG.width, 1, 0xFF0000FF);
		add(water);
		
		// It's not water without transparency...
		water = new FlxSprite(0, 201);
		water.makeGraphic(FlxG.width, FlxG.height-201, 0xFF0000FF);
		water.setAlpha(.2f);
		add(water);
	}
}

