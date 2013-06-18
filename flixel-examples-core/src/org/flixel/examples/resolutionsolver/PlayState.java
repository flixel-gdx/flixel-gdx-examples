package org.flixel.examples.resolutionsolver;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;

import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;

/**
 *
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	public static String ImgSquare = "examples/resolutionresolver/square.png";
	public static String ImgLogo = "examples/resolutionresolver/logo.png";

	@Override
	public void create()
	{
		Resolution[] resolutions = {	
				new Resolution(320, 480, "320480"), 
				new Resolution(480, 800, "480800"),
				new Resolution(600, 800, "800600")
			};
		FlxG.addResolutionResolver(resolutions);
		
		FlxG.setBgColor(0xFFFFFFFF);
		
		FlxSprite s = new FlxSprite(100, 100);
		s.loadGraphic(ImgSquare, false, false, 32, 32);
		add(s);
		
		FlxSprite logo = new FlxSprite(200, 200);
		logo.loadGraphic(ImgLogo, false, false);
		add(logo);
	}

}

