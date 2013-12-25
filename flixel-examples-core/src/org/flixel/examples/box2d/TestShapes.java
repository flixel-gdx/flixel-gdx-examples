package org.flixel.examples.box2d;

import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.collision.shapes.B2FlxChain;
import org.flxbox2d.collision.shapes.B2FlxCircle;
import org.flxbox2d.collision.shapes.B2FlxEdge;
import org.flxbox2d.collision.shapes.B2FlxPolygon;
import org.flxbox2d.collision.shapes.B2FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class TestShapes extends Test
{
	@Override
	public void create()
	{		
		super.create();
		title.setText("Shapes");
		info.setText("Box, Circle, Convex, Concave, Edge, Chain");
		
		// Box shape
		add(new B2FlxBox(50,50, 50, 50)
			.setRestitution(.3f)
			.setFriction(.2f)
			.setDensity(.8f)
			.setDraggable(true)
			.create());
		
		// Circle shape
		add(new B2FlxCircle(120, 50, 15f)
			.setRestitution(.3f)
			.setFriction(.2f)
			.setDensity(.8f)
			.setDraggable(true)
			.create());
		
				
		// Convex shape
		/*
		 * p1------------p2
		 * |	 		   \	  
		 * |	 		    \  
		 * |			     \
		 * |			     /p3
		 * |			    /
		 * |			   /
		 * p5------------p4
		 */
		add(new B2FlxPolygon(200, 50, new float[][][]
				{	
					{{-20,-20},{20,-20},{40,0},{20,20},{-20,20}}
				})			
		.setRestitution(.3f)
		.setFriction(.2f)
		.setDensity(.8f)
		.setDraggable(true)
		.create());
		
		
		// Concave shape
		/*
		 * p1------------p2-p5--p6
		 * |	 		 |	   /
		 * |	 		 |    /
		 * |			 |p7 /
		 * |			 |p8 \
		 * |			 |    \
		 * |			 |	   \
		 * p4------------p3-p10-p9
		 */
		add(new B2FlxPolygon(280, 50, new float[][][]
					{	
						{{-20,-20},{20,-20},{20,20},{-20,20}},
						{{20,-20},{40,-20},{20,0}},
						{{20, 0},{40,20},{20,20}}
					})			
			.setRestitution(.3f)
			.setFriction(.2f)
			.setDensity(.8f)
			.setDraggable(true)
			.create());
		
		
		// Edge shape
		add(new B2FlxEdge(300, 120, new float[][]{{0,0},{200, -40},{300,30}}).create());
		
		// Chain shape. This can also be a loop, the begin and end point will connect.
		add(new B2FlxChain(100, 100, new float[][]{{0,0},{100, 0},{150,50}})
				.setPrevVertex(-5, -5f)
				.setNextVertex(150f, 55f)
				.create()
			);
			
		
		
		// Shapeless. Attach shapes to it.
		B2FlxSprite sprite = new B2FlxSprite(200, 200)
			.setDraggable(true)
			.create();
		
		add(sprite);
		
		B2FlxPolygon polyshape = new B2FlxPolygon(0, 0, new float[][][]
			{
				{{-64,-64},{32,-32},{32,32},{-32,32}}
			})
			.setRestitution(.3f)
			.setFriction(.2f)
			.setDensity(.8f);		
		sprite.createFixtureFromPolygon(polyshape, true);
		/*
		polyshape = new B2FlxPolygon(0, 0, new float[][][]
			{	
				{{-32,-32},{64,-64},{32,32},{-32,32}}
			})
			.setRestitution(.3f)
			.setFriction(.2f)
			.setDensity(.8f)
			.setSensor(true);		
		sprite.createFixtureFromPolygon(polyshape, true);		*/
	}
}

