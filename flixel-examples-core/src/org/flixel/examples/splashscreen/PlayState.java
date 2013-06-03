package org.flixel.examples.splashscreen;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.event.IFlxCamera;

/**
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	private static int CENTER_X = FlxG.width / 2;
	private static int CENTER_Y = FlxG.height / 2;
	private static String ImgGearBlue = "examples/splashscreen/pack:gear_blue";
	private static String ImgGearPurple = "examples/splashscreen/pack:gear_purple";
	private static String ImgGearGrey = "examples/splashscreen/pack:gear_grey";
	private static String ImgGearGreen = "examples/splashscreen/pack:gear_green";
	private static String ImgPoweredBy = "examples/splashscreen/pack:powered_by";
	private static String ImgFlixelGDX = "examples/splashscreen/pack:flixel-gdx";
	
	private float _counter = 3f;

	@Override
	public void create()
	{
		FlxG.width = FlxG.camera.viewportWidth;
		FlxG.height = FlxG.camera.viewportHeight;
		CENTER_X = FlxG.width / 2;
		CENTER_Y = FlxG.height / 2;
		
		FlxG.setBgColor(0xFFFFFFFF);

		add(new Gear(ImgGearBlue, CENTER_X - 160, CENTER_Y - 198, 1.822f));
		add(new Gear(ImgGearPurple, CENTER_X + 18, CENTER_Y - 193, 2.55f));
		add(new Gear(ImgGearGrey, CENTER_X - 3, CENTER_Y - 114, -1.5f));
		add(new Gear(ImgGearGreen, CENTER_X - 87, CENTER_Y - 10, 2.55f));

		add(new Lightbulb(CENTER_X + 54, CENTER_Y - 68));
		add(new Heart(CENTER_X - 55, CENTER_Y + 30));
		add(new Code(CENTER_X + 41, CENTER_Y + -163));
		add(new DPad(CENTER_X - 105, CENTER_Y - 143));

		add(new FlxSprite(CENTER_X - 63, CENTER_Y + 113, ImgPoweredBy));
		add(new FlxSprite(CENTER_X - 129, CENTER_Y + 140, ImgFlixelGDX));

		FlxG.camera.flash(0x00000000, .25f);
	}
	
	@Override
	public void update()
	{
		super.update();
		if(_counter > 0)
		{
			_counter -= FlxG.elapsed;
			if(_counter <= 0)
			{
				FlxG.fade(0xFF000000, .75f, new IFlxCamera()
				{
					@Override
					public void callback()
					{
						FlxG.switchState(new HelloState());
					}
				});
			}
		}
	}
}
