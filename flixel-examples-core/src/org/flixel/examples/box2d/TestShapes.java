package org.flixel.examples.box2d;

import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxEdge;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxPolygon;

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
		add(new B2FlxBox(50,50, 20, 20)
			.setRestitution(.3f)
			.setFriction(.2f)
			.setDensity(.8f)
			.create());
		
		
		// Circle shape
		add(new B2FlxCircle(120, 50, 15f)
			.setRestitution(.3f)
			.setFriction(.2f)
			.setDensity(.8f)
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
		add(new B2FlxPolygon(200, 100, new float[][][]
				{	
					{{-20,-20},{20,-20},{40,0},{20,20},{-20,20}}
				})			
		.setRestitution(.3f)
		.setFriction(.2f)
		.setDensity(.8f)
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
		add(new B2FlxPolygon(280, 100, new float[][][]
					{	
						{{-20,-20},{20,-20},{20,20},{-20,20}},
						{{20,-20},{40,-20},{20,0}},
						{{20, 0},{40,20},{20,20}}
					})			
			.setRestitution(.3f)
			.setFriction(.2f)
			.setDensity(.8f)
			.create());
		
		
		// Edge shape
		add(new B2FlxEdge(300, 100, new float[][]{{10,100},{200, 50},{300,100}}).create());
		
		
		// Chain shape
		// TODO: add chain shape.
	}
}

