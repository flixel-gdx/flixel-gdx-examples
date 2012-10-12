package org.flixel.examples.box2d;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxText;
import org.flixel.event.AFlxButton;
import org.flixel.plugin.flxbox2d.B2FlxB;
import org.flixel.plugin.flxbox2d.B2FlxState;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxSprite;
import org.flixel.plugin.flxbox2d.system.debug.B2FlxDebug;

/**
 *
 * @author Ka Wing Chin
 */
public class Test extends B2FlxState
{
	private Test[] tests;
	public static int currentTest = 0;
	public FlxText title;
	public FlxText info;
	
	@Override
	public void create()
	{		
		super.create();
		FlxG.debug = true;
		
		// Debug renderer.
		FlxG.addPlugin(new B2FlxDebug());
		
		B2FlxB.world.setWarmStarting(true);
		
		add(new FlxText(FlxG.width-190, 5, 180, "FlxBox2D for 2.55").setFormat(null, 8, 0xFFFFFF, "right"));
		add(new FlxText(-10, 16, FlxG.width, "'Left/Right' arrows to go to previous/next example\n 'R' to reset.")
		.setFormat(null, 8, 0xFFFFFF, "right"));
		add(title = new FlxText(-10, 48, FlxG.width, "Hello").setFormat(null, 16, 0x00CCFF, "right")); 
		add(info = new FlxText(10, 5, FlxG.width, "").setFormat(null, 8, 0xFFFFFF));
		
		// Create walls
		// Left
		new B2FlxBox(0, 0, 5, FlxG.height).setType(B2FlxSprite.STATIC).create();
		// Right
		new B2FlxBox(FlxG.width-5, 0, 5, FlxG.height).setType(B2FlxSprite.STATIC).create();
		// Top
		new B2FlxBox(0, 0, FlxG.width, 5).setType(B2FlxSprite.STATIC).create();
		// Bottom
		new B2FlxBox(0, FlxG.height-5, FlxG.width, 5).setType(B2FlxSprite.STATIC).create();
		
		tests = new Test[]	{
								new TestShapes(),			// Shapes
								new TestDistanceJoint(),	// DistanceJoint
								new TestRopeJoint(),		// RopeJoint
								new TestRevoluteJoint(),	// RevoluteJoint
								new TestPrismaticJoint(),	// PrismaticJoint
								new TestPulleyJoint(),		// PulleyJoint
								new TestGearJoint(),		// GearJoint
								new TestFrictionJoint(),	// FrictionJoint
								new TestWeldJoint(),		// WeldJoint
								new TestWheelJoint(),		// WheelJoint
								
								new TestRagdolls(),			// Ragdolls
								new TestCompound(),			// Compound Shapes
								new TestCrankGearsPulley(), // Crank Gears Pulley
								new TestBridge(),			// Bridge
								new TestStack(),			// Stack
								new TestCCD(),				// Continuous Collision Detection
//								new TestTheoJansen,			// Theo Jansen
//								new TestEdges(),			// Edges
//								new TestBuoyancy(),			// Buouyancy
								new TestOneSidedPlatform(), // One Sided Platform
//								new TestBreakable(),		// Breakable
//								new TestRaycast(),			// Raycast
//								new TestSensor(),			// Sensor
							};
		
		
		// Mobile
		if(FlxG.mobile)
		{
			FlxButton prevButton = new FlxButton(2, FlxG.height - 20, "Previous", new AFlxButton(){@Override public void callback(){prev();}});
			prevButton.setSolid(false);
			add(prevButton);
			
			FlxButton nextButton = new FlxButton(82, FlxG.height - 20, "Next", new AFlxButton(){@Override public void callback(){next();}});
			nextButton.setSolid(false);
			add(nextButton);
			
			FlxButton resetButton = new FlxButton(162, FlxG.height - 20, "Reset", new AFlxButton(){@Override public void callback(){reset();}});
			resetButton.setSolid(false);
			add(resetButton);
		}
	}
	
	public B2FlxBox createBox(float x, float y, float width, float height)
	{
		return (B2FlxBox) new B2FlxBox(x, y, width, height)
		.setFriction(.8f)
		.setRestitution(.3f)
		.setDensity(.7f)
		.create();
	}
	
	@Override
	public void update()
	{
		if(FlxG.keys.justPressed("RIGHT"))
			next();
		else if(FlxG.keys.justPressed("LEFT"))
			prev();
		else if(FlxG.keys.justPressed("R"))
			reset();
		super.update();
	}
	
	private void next()
	{
		if(tests.length <= ++currentTest)
			currentTest = 0;
		FlxG.switchState(tests[currentTest]);
	}
	
	private void prev()
	{
		if(0 > --currentTest)
			currentTest = tests.length-1;
		FlxG.switchState(tests[currentTest]);
	}
	
	private void reset()
	{
		FlxG.resetState();
	}
	
	@Override
	public void destroy()
	{		
		super.destroy(); 
		FlxG.removePluginType(B2FlxDebug.class);
	}
}

