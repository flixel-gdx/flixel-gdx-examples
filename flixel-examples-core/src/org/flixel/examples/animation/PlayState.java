package org.flixel.examples.animation;

import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.event.IFlxAnim;

/**
 * A simple demo to show how animation works.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	public final String ImgFlixelLogo = "examples/animation/pack:flixellogo";
	public final String ImgZombie = "examples/animation/pack:zombietxai"; // by Txai Viegas
	public final String ImgDroid = "examples/animation/pack:droid";
	public final String ImgZombie2 = "examples/animation/pack:zombie2";
	public final String ImgZombie3 = "examples/animation/pack:zombie3";
	public final String ImgChest = "examples/animation/pack:chest";
	public final String ImgBat = "examples/animation/pack:bat";
	public final String ImgGreenPotion = "examples/animation/pack:greenpotion";
	
	
	private FlxSprite _zombie;

	@Override
	public void create()
	{
		// Shiny flixel logo.
		FlxSprite s = new FlxSprite(20, 20);
		s.loadGraphic(ImgFlixelLogo, true);
		s.addAnimation("shine", new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4}, 14);
		s.play("shine");
		s.drawLine(0, 0, 30, 30, 0xFFFF00FF);
		add(s);
		
		// NOTE: the zombie image is facing to the left. Normally you should stick sprites facing to the right.
		s = new FlxSprite(20, 60).loadGraphic(ImgZombie, true, true, 36, 41);
		s.addAnimation("walk", new int[]{0, 1, 2, 3, 4, 5}, 12);
		s.setFacing(FlxSprite.RIGHT);
		s.play("walk");
		add(s);
		
		
		// Zombie with more frames added to slow down an animation.
		s = new FlxSprite(100, 60).loadGraphic(ImgZombie, true, true, 36, 41);
		s.addAnimation("head bounce", new int[]{7, 7, 8, 9, 10, 11, 11, 11, 10, 10, 10, 9, 9, 8, 8, 7, 7, 7}, 10);
		s.play("head bounce");
		add(s);
		
		
		// Another zombie, but this can move on the x-axis and there is also animation callback.
		_zombie = new FlxSprite(20, 120).loadGraphic(ImgZombie, true, true, 36, 41);
		_zombie.addAnimation("walk", new int[]{0, 1, 2, 3, 4, 5}, 2);
		_zombie.setFacing(FlxSprite.LEFT);
		_zombie.velocity.x = 5;
		_zombie.play("walk");
		_zombie.addAnimationCallback(AnimationCallback);
		add(_zombie);
		
		
		// Droid
		s = new FlxSprite(220, 120).loadGraphic(ImgDroid, true, false, 14, 14);
		s.addAnimation("walk", new int[]{0,1,2,3}, 20, true);
		s.play("walk");
		add(s);
		
		// Zombie from Mysterious Mayhem
		s = new FlxSprite(20, 200).loadGraphic(ImgZombie2, true, false, 24, 39);
		s.addAnimation("walk", new int[]{0,1,0,2}, 3);
		s.play("walk");
		add(s);
		
		// Another zombie from Mysterious Mayhem
		s = new FlxSprite(70, 200).loadGraphic(ImgZombie3, true, false, 24, 39);
		s.addAnimation("dig", new int[]{3,3,4,5,6,7,0,1,0,2,7,7,6,6,5,5,4,4,3,3}, 7);
		s.play("dig");
		add(s);
		
		// Chest from Mysterious Mayhem
		s = new FlxSprite(150, 200).loadGraphic(ImgChest, true, false, 18, 23);
		s.addAnimation("open", new int[]{0,0,0,1,2,3,4,5,6,7,8,9,8,7,6,5,4,10,10,10}, 5);
		s.play("open");
		add(s);
		
		// Bat from Mysterious Mayhem
		s = new FlxSprite(70, 40).loadGraphic(ImgBat, true, false, 28, 23);
		s.addAnimation("fly", new int[]{0,1},5);
		s.play("fly");
		add(s);
		
		// Green potion from Mysterious Mayhem
		s = new FlxSprite(120, 20).loadGraphic(ImgGreenPotion, true, false, 11, 33);
		s.addAnimation("default", new int[]{0,1,2},10);
		s.play("default");
		//s.scale.x=4;
//		s = new FlxSprite(120, 20, ImgGreenPotion);
		add(s);
	}

	
	IFlxAnim AnimationCallback = new IFlxAnim()
	{
		@Override
		public void callback(String curAnim, int curFrame, int curIndex)
		{
			if(curFrame == 5)
			{
				if(_zombie.getFacing() == FlxSprite.LEFT)
				{
					_zombie.setFacing(FlxSprite.RIGHT);
					_zombie.velocity.x = -5;
				}
				else
				{
					_zombie.setFacing(FlxSprite.LEFT);
					_zombie.velocity.x = 5;
				}
			}
		}
	};
}