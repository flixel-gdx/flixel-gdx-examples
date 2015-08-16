package org.flixel.examples.flappybalt;

import org.flixel.*;
	
public class Paddle extends FlxSprite
{
	protected String ImgPaddle = "examples/flappybalt/pack:paddle";
		
	static public int SPEED = 480;
		
	public int targetY = 0;
		
	public Paddle(float X,int Facing)
	{
		super(X, FlxG.height);
		loadGraphic(ImgPaddle,false,true);
		setFacing(Facing);
	}
		
	public void randomize()
	{
		targetY = (int)(16 + FlxG.random()*(208-height));
		if(targetY < y)
			velocity.y = -SPEED;
		else
			velocity.y = SPEED;
	}
		
	@Override
	public void update()
	{
		if( ((velocity.y < 0) && (y <= targetY + SPEED*FlxG.elapsed)) ||
			((velocity.y > 0) && (y >= targetY - SPEED*FlxG.elapsed)) )
		{
			velocity.y = 0;
			y = targetY;
		}
	}
}