package org.flixel.examples.distancefieldfont;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

/**
 * A test for distance field font.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{	
	private String font_default = "examples/distancefieldfont/droid_default.fnt";
	private String font_distance = "examples/distancefieldfont/droid_distance.fnt";
	
	private float[] scales;
	private Array<FlxText> smoothTexts;
	private Array<FlxText> defaultTexts;
	private boolean enabled = true;
	private boolean firstRender;
	
	@Override
	public void create()
	{
		ShaderProgram.pedantic = false;
		
		smoothTexts = new Array<FlxText>();
		defaultTexts = new Array<FlxText>();
		
		// The scales that will be used for the text.
		scales = new float[]{.25f, .5f, .75f, 1f, 1.25f, 1.5f, 1.75f, 2f, 2.25f, 2.5f, 3.5f};//
		
		// Create the smooth texts.
		createTexts(smoothTexts, scales, true);
		
		// Create the default texts.
		createTexts(defaultTexts, scales, false);
		
		// Display manual.
		FlxText text = new FlxText(10, FlxG.height-40, FlxG.width, "- Touch to change rendering -");
		text.setAlignment("center");
		add(text);
	}
	
	private void createTexts(Array<FlxText> array, float[] scales, boolean enableDistanceField)
	{
		FlxText text;
		for(int i = 0; i < scales.length; i++)
		{
			text = new FlxText(0, 0, FlxG.width, "flixel-gdx");
			if(enableDistanceField)
			{
				/**
				 * Enable distance field
				 * The padding is 4 and the smoothness is 0.3.
				 */
				//text.setDistanceField(true, 4, 0.3f, "fontShader");
				text.setFormat(font_distance, 32, 0xFFFFFF, null, 0xFFFF0000);				
			}
			else
			{				
				text.setFormat(font_default, 32, 0xFFFFFF, null, 0xFFFF0000);				
			}			
			text.scale.x = text.scale.y = scales[i];
			add(text);			
			array.add(text);
		}
	}

	@Override
	public void draw()
	{
		super.draw();
		if(!firstRender)
		{
			firstRender = true;
			float prev = 0;
			FlxText text;
			for(int i = 0; i < scales.length; i++)
			{
				text = smoothTexts.get(i);
				text.y = prev;
				
				text = defaultTexts.get(i);
				text.y = prev;
				text.visible = false;
				
				prev = text.y + text.height;
			}			
		}
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if(FlxG.mouse.justPressed())
		{
			enabled = !enabled ;
			for(int i = 0; i < scales.length; i++)
			{
				smoothTexts.get(i).visible = enabled;
				defaultTexts.get(i).visible = !enabled;
			}
		}
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		FlxG.disposeShader("fontShader");
	}
}

