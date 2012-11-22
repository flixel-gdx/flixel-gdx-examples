package org.flixel.examples.tweening;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.plugin.tweens.TweenPlugin;
import org.flixel.plugin.tweens.TweenSprite;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

/**
 *
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	@Override
	public void create()
	{
		FlxG.addPlugin(new TweenPlugin());
		
		FlxSprite sprite = new FlxSprite(0, 0);
		sprite.makeGraphic(50, 50, 0xFFFF00FF);
		add(sprite);
		
		FlxSprite sprite2 = new FlxSprite(200, 50);
		sprite2.makeGraphic(50, 50, 0xFF0000FF);
		add(sprite2);
		
		FlxSprite sprite3 = new FlxSprite(300, 100);
		sprite3.makeGraphic(50, 50, 0xFFFF0000);
		add(sprite3);

		FlxSprite sprite4 = new FlxSprite(200, 200);
		sprite4.makeGraphic(50, 50, 0xFFFFFF00);
		add(sprite4);
			
		
		// Sprite 1 - position and scale
		Tween.to(sprite, TweenSprite.XY, 1f)
			.target(100, 200)
			.repeatYoyo(2, 0.5f)
			.start(TweenPlugin.manager);
		Tween.to(sprite, TweenSprite.SCALE_XY, 1f)
			.target(2, 2)
			.repeatYoyo(2, 0.5f)
			.start(TweenPlugin.manager);
		
		// Sprite 2 - angle
		Tween.to(sprite2, TweenSprite.ANGLE, .75f)
			.target(360)
			.repeat(2, 1f)
			.start(TweenPlugin.manager);
		
		
		// Sprite 3 - timeline
		Timeline.createSequence()
			.push(Tween.to(sprite3, TweenSprite.X, .5f).target(FlxG.width-50))
			.push(Tween.to(sprite3, TweenSprite.Y, .5f).target(FlxG.height-50))
			.push(Tween.to(sprite3, TweenSprite.X, .5f).target(sprite3.x))
			.push(Tween.to(sprite3, TweenSprite.Y, .5f).target(sprite3.y))
			.repeatYoyo(1, 0)
			.start(TweenPlugin.manager);
		
		
		// Sprite 4 - timeline + parallel
		Timeline.createSequence()			
			.beginParallel()
				.push(Tween.to(sprite4, TweenSprite.ANGLE, .5f).target(180f))
				.push(Tween.to(sprite4, TweenSprite.SCALE_XY, .5f).target(.5f, .5f))
				.push(Tween.to(sprite4, TweenSprite.ALPHA, .5f).target(0.5f))
				.push(Tween.to(sprite4, TweenSprite.X, .5f).target(250))
			.end()
			.repeatYoyo(4, 0f) 
			// TODO: bug when there is a delay and a negative number for repeat. See ISSUE 14 at
			// http://code.google.com/p/java-universal-tween-engine/issues/detail?id=14
		.start(TweenPlugin.manager);
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.getPlugin(TweenPlugin.class).destroy();
		FlxG.removePluginType(TweenPlugin.class);
	}
}