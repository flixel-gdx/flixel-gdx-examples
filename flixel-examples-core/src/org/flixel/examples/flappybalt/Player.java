package org.flixel.examples.flappybalt;

import org.flixel.*;
import org.flixel.event.IFlxCamera;
	
public class Player extends FlxSprite
{
	protected String ImgDove = "examples/flappybalt/pack:dove";
		
	public Player()
	{
		super(FlxG.width*0.5f-4, FlxG.height*0.5f-4);
		loadGraphic(ImgDove,true,true);
		setFrame(2);
		addAnimation("flap",new int[]{1,0,1,2},12,false);
	}
		
	@Override
	public void update()
	{
		if(FlxG.keys.justPressed("SPACE") || FlxG.mouse.justPressed())
		{
			if(acceleration.y == 0)
			{
				acceleration.y = 500;
				velocity.x = 80;
			}
			velocity.y = -240;
			play("flap",true);
		}
	}
		
	@Override
	public void kill()
	{
		if(!exists)
			return;
		super.kill();
		FlxG.flash(0xffffffff,1,new IFlxCamera(){@Override public void callback(){onFlashDone();}});
		FlxG.shake(0.02f,0.35f);
	}
		
	public void onFlashDone()
	{
		PlayState.saveScore();
		FlxG.resetState();
	}
}