package org.flixel.examples.box2d;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxU;
import org.flixel.event.IFlxButton;
import org.flxbox2d.B2FlxB;
import org.flxbox2d.B2FlxState;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.collision.shapes.B2FlxShape;
import org.flxbox2d.dynamics.joints.B2FlxMouseJoint;
import org.flxbox2d.system.debug.B2FlxDebug;

import com.badlogic.gdx.utils.Array;

/**
 * A parent class for testing.
 * 
 * @author Ka Wing Chin
 */
public class Test extends B2FlxState
{
	private static Array<Class<?extends FlxState>> tests;
	public static int currentTest = 0;
	public FlxText title;
	public FlxText info;
	public B2FlxMouseJoint mouse;
	public final short WALL = 0x0001;
	
	@Override
	public void create()
	{		
		super.create();
		B2FlxB.setGravity(0, 9.8f);
		FlxG.visualDebug = true;
		FlxG.setBgColor(0xff000000);
		
		if(FlxG.mobile)
			B2FlxDebug.drawCollisions = false;
//		B2FlxDebug.drawAABBs = true;
		
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
		add(new B2FlxBox(0, 0, 5, FlxG.height).setType(B2FlxShape.STATIC).setCategoryBits((short) WALL).create());
		// Right
		add(new B2FlxBox(FlxG.width-5, 0, 5, FlxG.height).setType(B2FlxShape.STATIC).setCategoryBits((short) WALL).create());
		// Top
		add(new B2FlxBox(0, 0, FlxG.width, 5).setType(B2FlxShape.STATIC).setCategoryBits((short) WALL).create());
		// Bottom
		add(new B2FlxBox(0, FlxG.height-5, FlxG.width, 5).setType(B2FlxShape.STATIC).setCategoryBits((short) WALL).create());
		
		// Add mouse joint.
		add(mouse = new B2FlxMouseJoint());
		
		if(tests == null)
		{
			tests = new Array<Class<? extends FlxState>>();
			tests.add(TestShapes.class);			// Shapes
			tests.add(TestDistanceJoint.class);		// DistanceJoint
			tests.add(TestRopeJoint.class);			// RopeJoint
			tests.add(TestRevoluteJoint.class);		// RevoluteJoint
			tests.add(TestPrismaticJoint.class);	// PrismaticJoint
			tests.add(TestPulleyJoint.class);		// PrismaticJoint
			tests.add(TestGearJoint.class);			// GearJoint
			tests.add(TestFrictionJoint.class);		// FrictionJoint
			tests.add(TestWeldJoint.class);			// WeldJoint
			tests.add(TestWheelJoint.class);		// WheelJoint
			tests.add(TestCart.class);				// Cart
			tests.add(TestRagdolls.class);			// Ragdolls
			tests.add(TestCompound.class);			// Compound Shapes
			tests.add(TestCrankGearsPulley.class);	// Crank Gears Pulley
			tests.add(TestBridge.class);			// Bridge
			tests.add(TestStack.class);				// Stack
			tests.add(TestCCD.class);				// Continuous Collision Detection
			tests.add(TestBuoyancy.class);			// Buouyancy 
			tests.add(TestGravity.class);			// Gravity
			tests.add(TestOneSidedPlatform.class);	// One Sided Platform
			tests.add(TestBreakable.class);			// Breakable
			tests.add(TestSensor.class);			// Sensor
			tests.add(TestCollisionDetection.class);// Collision Detection
			tests.add(TestExplosion.class);			// Explosion & Implosion
						
//			TheoJansen.class 			// Theo Jansen
//			TestEdges.class,			// Edges
//			TestRaycast.class,			// Raycast				
		};			
		
		
		// Mobile
		if(FlxG.mobile)
		{
			add(createButton(2, FlxG.height - 20, "Previous", new IFlxButton(){@Override public void callback(){prev();}}));
			add(createButton(82, FlxG.height - 20, "Next", new IFlxButton(){@Override public void callback(){next();}}));
			add(createButton(162, FlxG.height - 20, "Reset", new IFlxButton(){@Override public void callback(){reset();}}));
		}
		
		if(FlxU.getClassName(this, true).equals("Test"))
		{
			try
			{				
				FlxG.switchState((B2FlxState)tests.get(currentTest).newInstance());
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
		return new B2FlxBox(x, y, width, height)
		.setFriction(.8f)
		.setRestitution(.3f)
		.setDensity(.7f)
		.setDraggable(true)
		.create();
	}
	
	public FlxButton createButton(float x, float y, String label, IFlxButton callback)
	{
		FlxButton button = new FlxButton(x, y, label, callback);
		button.ignoreDrawDebug = true;
		button.scrollFactor.x = button.scrollFactor.y = 0;
		button.setSolid(false);
		button.moves = false;
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
		if(tests.size <= ++currentTest)
			currentTest = 0;
		try
		{				
			FlxG.switchState((B2FlxState)tests.get(currentTest).newInstance());
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
			currentTest = tests.size-1;
		try
		{				
			FlxG.switchState((B2FlxState)tests.get(currentTest).newInstance());
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
	}
}

