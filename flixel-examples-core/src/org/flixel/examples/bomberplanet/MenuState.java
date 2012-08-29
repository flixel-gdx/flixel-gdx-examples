package org.flixel.examples.bomberplanet;

import org.flixel.*;
import org.flixel.event.AFlxCamera;

public class MenuState extends FlxState
{
	static public String ImgTitle = "examples/bomberplanet/data/pack:title"; 
	static public String ImgStars = "examples/bomberplanet/data/pack:star_particles"; 

	public FlxText clickText;
	public float blinkTimer;

	@Override
	public void create()
	{
		FlxEmitter emitter = new FlxEmitter();
		emitter.makeParticles(ImgStars,200,0,true,0);
		emitter.width = FlxG.width;
		emitter.y = FlxG.height;
		emitter.setYSpeed(-100,-20);
		emitter.setXSpeed();
		emitter.setRotation();
		emitter.start(false,0,0.1f);
		add(emitter);

		add(new FlxSprite(0,0,ImgTitle));
		
		FlxText t;
		t = new FlxText(0,2,FlxG.width,"a 48-hour game by Adam 'Atomic' Saltsman");
		t.setAlignment("center");
		t.setColor(0x00648c);
		add(t);
		
		clickText = new FlxText(FlxG.width/2-50,FlxG.height-111,100,"CLICK TO PLAY");
		clickText.setAlignment("center");
		add(clickText);
		blinkTimer = 0;

		FlxG.mouse.show();
	}

	@Override
	public void update()
	{
		super.update();

		if(FlxG.mouse.justPressed())
		{
			FlxG.mouse.hide();
			FlxG.fade(0xff000000,1,onFade);
			clickText.exists = false;
		}

		if(clickText.exists)
		{
			blinkTimer += FlxG.elapsed;
			if(blinkTimer - (int)blinkTimer < 0.2f)
				clickText.visible = false;
			else
				clickText.visible = true;
		}
	}

	public AFlxCamera onFade = new AFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new StoryState());
		}
	};

	static public void eraseData()
	{
		FlxSave save = new FlxSave();
		if(save.bind("escape"))
			save.erase();
		for(int i = 0; i < FlxG.levels.size; i++)
			FlxG.levels.set(i, false);
	}
}