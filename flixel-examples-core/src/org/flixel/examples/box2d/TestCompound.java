package org.flixel.examples.box2d;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxCircle;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxPolygon;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxSprite;
import org.flixel.plugin.flxbox2d.common.B2FlxV2;
import org.flixel.plugin.flxbox2d.common.math.B2FlxMath;

/**
 *
 * @author Ka Wing Chin
 */
public class TestCompound extends Test
{
	@Override
	public void create()
	{
		super.create();
		title.setText("Compound Shapes");
		
		B2FlxSprite body;
		B2FlxCircle cd1;
		B2FlxCircle cd2;
		
		float x;
		int i;
		for(i = 0; i < 5; i++)
		{
			cd1 = new B2FlxCircle(0, 0, 15f);
			cd2 = new B2FlxCircle(0, 0, 15f);
			cd2.setShapePosition(22,22);
			
			x = 320 + B2FlxMath.randomRange(-3, 3);
			body = (B2FlxSprite) new B2FlxSprite(x+150, (31.5f + 70 * -i + 300))
				.setAngle(FlxG.random() * 360f)
				.setDraggable(true)
				.create();
			body.createFixture(cd1, 2f);
			body.createFixture(cd2, 2f);
			add(body);
			
			cd1.disposeShape();
			cd2.disposeShape();
		}
		
		
		B2FlxBox b1;
		B2FlxBox b2;
		for(i = 0; i < 5; i++)
		{
			b1 = new B2FlxBox(0, 0, 15, 30);
			b2 = (B2FlxBox) new B2FlxBox(0, 0, 15, 30, new B2FlxV2(0,-15), 90);
			x = 320 + B2FlxMath.randomRange(-3, 3);
			body = (B2FlxSprite) new B2FlxSprite(x-150, (31.5f + 75 * -i + 300))
				.setAngle(FlxG.random() * 360f)
				.setDraggable(true)
				.create();
			body.createFixture(b1, 2f);
			body.createFixture(b2, 2f);
			add(body);
			
			b1.disposeShape();
			b2.disposeShape();
		}
		

		/*		p1  p4
		 *      /|  |\
		 *     / |  | \
		 *    /  |  |  \
		 *   /  /p2p6\  \
		 *  /  /      \  \
		 * //p3        p5\\ 
		 */
		for(i = 0; i < 5; i++)
		{
			add(new B2FlxPolygon(320, (-61.5f + 55* -i + 300), new float[][][]{
																				{{0,0}, {0,36}, {-27,56}},
																				{{0,0}, {27,56},{0,36}}				
																			})
					.setDraggable(true)	
					.create());
		}
		
		
		B2FlxBox bottom = new B2FlxBox(0, 0, 90, 9);
		B2FlxBox left = new B2FlxBox(0, 0, 9, 162, new B2FlxV2(-43.5f, -70.5f), -11.5f);
		B2FlxBox right = new B2FlxBox(0, 0, 9, 162, new B2FlxV2(43.5f, -70.5f), 11.5f);
		body = (B2FlxSprite) new B2FlxSprite(320, 300).setDraggable(true).create();
		body.createFixture(bottom, 4, true);
		body.createFixture(left, 4, true);
		body.createFixture(right, 4, true);
		add(body);
	}
}

