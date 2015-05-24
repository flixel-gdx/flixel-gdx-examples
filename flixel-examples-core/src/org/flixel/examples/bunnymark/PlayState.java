package org.flixel.examples.bunnymark;

import org.flixel.FlxBasic;
import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxTimer;
import org.flixel.FlxU;
import org.flixel.event.IFlxButton;
import org.flixel.event.IFlxTimer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

/**
 *
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	private final String ImgGrass = "examples/bunnymark/grass.png";
	private final String ImgFont40 = "examples/bunnymark/nokiafc.fnt";
	private final int INITIAL_AMOUNT = 500;
	private static final float INTERVAL = 5;
	
	private StringBuffer _buffer;
	private boolean _complex;	
	private boolean _collisions;
	
	private FlxTimer _memoryTimer;
	private FlxGroup _bunnies;
	private FlxButton _complexityButton;
	private FlxButton _collisionButton;
	private FlxText _bunnyCounter;
	private FlxText _memoryUsage;

	@Override
	public void create()
	{
		FlxG.debug = true;
		
		// Init basic stuff
		_buffer = new StringBuffer();
				
		// The grass background
		FlxSprite grass = new FlxSprite(0, 0).loadGraphic(ImgGrass, false, false, FlxG.width, FlxG.height);
		grass.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		add(grass);
		
		add(_bunnies = new FlxGroup());
		
		
		// Text display
		if(Gdx.app.getType() == ApplicationType.WebGL)
			add(_bunnyCounter = new FlxText(0, 20, FlxG.width).setFormat(ImgFont40, 40, 0xFF000000, "center"));
		else
			add(_bunnyCounter = new FlxText(0, 20, FlxG.width).setFormat(null, 32, 0xFF000000, "center"));
//		add(_memoryUsage = new FlxText(10, 5, 200).setFormat(null, 8, 0xFF000000));
		
		// Buttons Left side
		float leftBtnY = 20;
		/*@formatter:off*/
		add(new FlxButton(leftBtnY, 25, "+100", new IFlxButton(){@Override public void callback(){addBunnies(100);}}));
		add(new FlxButton(leftBtnY, 65, "+250", new IFlxButton(){@Override public void callback(){addBunnies(250);}}));
		add(new FlxButton(leftBtnY, 105, "+500", new IFlxButton(){@Override	public void callback(){addBunnies(500);}}));
		add(new FlxButton(leftBtnY, 145, "+1000", new IFlxButton(){@Override public void callback(){addBunnies(1000);}}));
		add(new FlxButton(leftBtnY + 90, 25, "-100", new IFlxButton(){@Override public void callback(){addBunnies(-100);}}));
		add(new FlxButton(leftBtnY + 90, 65, "-250", new IFlxButton(){@Override public void callback(){addBunnies(-250);}}));
		add(new FlxButton(leftBtnY + 90, 105, "-500", new IFlxButton(){@Override public void callback(){addBunnies(-500);}}));
		add(new FlxButton(leftBtnY + 90, 145, "-1000", new IFlxButton(){@Override public void callback(){addBunnies(-1000);}}));
		/*@formatter:on*/
		
		// Buttons right side
		float rightBtnX = FlxG.width - 100;
		add(_complexityButton = new FlxButton(rightBtnX, 25, "Complex", new IFlxButton()
		{			
			@Override
			public void callback()
			{
				_complex = !_complex;
				_complexityButton.label.setText(_complex ? "Simple" : "Complex");
				
				for(FlxBasic bunny : _bunnies.members)
					((Bunny)bunny).setComplex(_complex);
			}
		}));
		
		add(_collisionButton = new FlxButton(rightBtnX, 65, "Collision ON", new IFlxButton()
		{			
			@Override
			public void callback()
			{
				_collisions = !_collisions;
				_collisionButton.label.setText(_collisions ? "Collision OFF" : "Collision ON");
			}
		}));
		
		
		// Finally create the bunnies
		addBunnies(INITIAL_AMOUNT);
		
		// Force GC
		System.gc();
		
		// Timer to update the memory usage
//		_memoryTimer = new FlxTimer();
//		updateMemoryUsage();
	}
	
	@Override
	public void update()
	{
		super.update();
		if(_collisions)
			FlxG.collide(_bunnies);
	}
	
	public void addBunnies(int amount)
	{
		float rx;
		float ry;
		if(amount > 0)
		{
			for(int i = 0; i < amount; i++)
			{
				rx = FlxG.random() * (FlxG.width - 26);
				ry = FlxG.random() * (FlxG.height - 37);
				_bunnies.add(new Bunny(rx, ry));
			}			
		}
		else if(amount < 0)
		{
			amount = (int) FlxU.abs(amount);
			FlxBasic bunny;
			for(int i = 0; i < amount; i++)
			{
				bunny = _bunnies.getFirstAlive();
				if(bunny != null)
				{
					bunny = _bunnies.remove(bunny, true);
					bunny.destroy();
				}
			}
			_bunnies.members.shrink();
		}
		
		_buffer.delete(0, _buffer.length())
		.append("Bunnies: ")
		.append(_bunnies.length);
		_bunnyCounter.setText(_buffer.toString());
	}

	public void updateMemoryUsage()
	{
		// Infinite loop
		_memoryTimer.start(INTERVAL, 1, updateMemoryCallback);
	}
	
	IFlxTimer updateMemoryCallback = new IFlxTimer()
	{			
		@Override
		public void callback(FlxTimer Timer)
		{
			_buffer.delete(0, _buffer.length())
			.append((Gdx.app.getJavaHeap() + Gdx.app.getNativeHeap()) / 1048576)
			.append(" MB");
			_memoryUsage.setText(_buffer.toString());
			updateMemoryUsage();
		}
	};
}
