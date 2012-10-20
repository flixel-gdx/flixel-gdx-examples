package org.flixel.examples.box2d;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxText;
import org.flixel.FlxU;
import org.flixel.event.AFlxButton;
import org.flixel.plugin.flxbox2d.B2FlxB;
import org.flixel.plugin.flxbox2d.B2FlxState;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxShape;
import org.flixel.plugin.flxbox2d.dynamics.joints.B2FlxMouseJoint;
import org.flixel.plugin.flxbox2d.system.debug.B2FlxDebug;

/**
 * A parent class for testing.
 * 
 * @author Ka Wing Chin
 */
public class Test extends B2FlxState
{
	@SuppressWarnings("rawtypes")
	private static Class[] tests;
	public static int currentTest = 0;
	public FlxText title;
	public FlxText info;
	public B2FlxMouseJoint mouse;
	
	@Override
	public void create()
	{		
		super.create();
		FlxG.debug = true;
		FlxG.visualDebug = true;
		
		// Debug renderer.
		FlxG.addPlugin(new B2FlxDebug());
		
		B2FlxB.world.setWarmStarting(true);
		
		FlxG.camera.setBounds(0,0,1024,640,true);
		
		FlxText text = new FlxText(FlxG.width-190, 5, 180, "FlxBox2D for 2.55").setFormat(null, 8, 0xFFFFFF, "right");
		text.scrollFactor.x = text.scrollFactor.y = 0;
		text.ignoreDrawDebug = true;
		add(text);

		add(text = new FlxText(-10, 16, FlxG.width, "'Left/Right' arrows to go to previous/next example\n 'R' to reset.")
		.setFormat(null, 8, 0xFFFFFF, "right"));
		text.scrollFactor.x = text.scrollFactor.y = 0;
		text.ignoreDrawDebug = true;

		add(title = new FlxText(-10, 48, FlxG.width, "").setFormat(null, 16, 0x00CCFF, "right"));
		title.scrollFactor.x = title.scrollFactor.y = 0;
		title.ignoreDrawDebug = true;
		add(info = new FlxText(10, 5, FlxG.width, "").setFormat(null, 8, 0xFFFFFF));
		info.scrollFactor.x = info.scrollFactor.y = 0;
		info.ignoreDrawDebug = true;
		
		// Create walls
		// Left
		add(new B2FlxBox(0, 0, 5, FlxG.height).setType(B2FlxShape.STATIC).create());
		// Right
		add(new B2FlxBox(FlxG.width-5, 0, 5, FlxG.height).setType(B2FlxShape.STATIC).create());
		// Top
		add(new B2FlxBox(0, 0, FlxG.width, 5).setType(B2FlxShape.STATIC).create());
		// Bottom
		add(new B2FlxBox(0, FlxG.height-5, FlxG.width, 5).setType(B2FlxShape.STATIC).create());
		
		// Add mouse joint.
		add(mouse = new B2FlxMouseJoint());
		
		if(tests == null)
		{
			tests = new Class[]	
			{
					TestShapes.class,			// Shapes
					TestDistanceJoint.class,	// DistanceJoint
					TestRopeJoint.class,		// RopeJoint
					TestRevoluteJoint.class,	// RevoluteJoint
					TestPrismaticJoint.class,	// PrismaticJoint
					TestPulleyJoint.class,		// PulleyJoint
					TestGearJoint.class,		// GearJoint
					TestFrictionJoint.class,	// FrictionJoint
					TestWeldJoint.class,		// WeldJoint
					TestWheelJoint.class,		// WheelJoint
					TestCart.class,				// Cart
					
					TestRagdolls.class,			// Ragdolls
					TestCompound.class,			// Compound Shapes
					TestCrankGearsPulley.class, // Crank Gears Pulley
					TestBridge.class,			// Bridge
					TestStack.class,			// Stack
					TestCCD.class,				// Continuous Collision Detection
//					TestTheoJansen.class,		// Theo Jansen
//					TestEdges.class,			// Edges
//					TestBuoyancy.class,			// Buouyancy
					TestOneSidedPlatform.class, // One Sided Platform
//					TestBreakable.class,		// Breakable
//					TestRaycast.class,			// Raycast
//					TestSensor.class,			// Sensor
			};			
		}
		
		// Mobile
		if(FlxG.mobile)
		{
			add(createButton(2, FlxG.height - 20, "Previous", new AFlxButton(){@Override public void callback(){prev();}}));
			add(createButton(82, FlxG.height - 20, "Next", new AFlxButton(){@Override public void callback(){next();}}));
			add(createButton(162, FlxG.height - 20, "Reset", new AFlxButton(){@Override public void callback(){reset();}}));
		}
		
		if(FlxU.getClassName(this, true).equals("Test"))
		{
			try
			{				
				FlxG.switchState((B2FlxState)tests[currentTest].newInstance());
			}
			catch(Exception e)
			{
				FlxG.log(e.getMessage());
				return;
			}
		}
	}
	
	public B2FlxBox createBox(float x, float y, float width, float height)
	{
		return (B2FlxBox) new B2FlxBox(x, y, width, height)
		.setFriction(.8f)
		.setRestitution(.3f)
		.setDensity(.7f)
		.setDraggable(true)
		.create();
	}
	
	public FlxButton createButton(float x, float y, String label, AFlxButton callback)
	{
		FlxButton button = new FlxButton(x, y, label, callback);
		button.ignoreDrawDebug = true;
		button.scrollFactor.x = button.scrollFactor.y = 0;
		button.setSolid(true);
		return button;
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
		try
		{				
			FlxG.switchState((B2FlxState)tests[currentTest].newInstance());
		}
		catch(Exception e)
		{
			FlxG.log(e.getMessage());
			return;
		}
	}
	
	private void prev()
	{
		if(0 > --currentTest)
			currentTest = tests.length-1;
		try
		{				
			FlxG.switchState((B2FlxState)tests[currentTest].newInstance());
		}
		catch(Exception e)
		{
			FlxG.log(e.getMessage());
			return;
		}
	}
	
	private void reset()
	{
		FlxG.resetState();
	}
	
	@Override
	public void destroy()
	{		
		super.destroy();
		title = null;
		info = null;
		mouse = null;
		FlxG.removePluginType(B2FlxDebug.class);
	}
}

