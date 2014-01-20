package org.flixel.examples.quadtree;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxObject;
import org.flixel.FlxPoint;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxU;
import org.flixel.event.IFlxButton;
import org.flixel.event.IFlxCollision;

import com.badlogic.gdx.graphics.Texture.TextureWrap;

/**
 * A Quadtree demonstration, based on the Flash application of Michael Baczynski.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	private final int WORLD_WIDTH = 480;
	private final int WORLD_HEIGHT = 800;
	private final FlxPoint _last = new FlxPoint();
	
	private static final String ImgBg = "examples/quadtree/bg.png";
	private static final String Img2 = "examples/quadtree/pack:2";
	private static final String Img4 = "examples/quadtree/pack:4";
	private static final String Img6 = "examples/quadtree/pack:6";
	private static final String Img20 = "examples/quadtree/pack:20";
	private static final String Img30 = "examples/quadtree/pack:30";
	private static final String ImgOverlap8 = "examples/quadtree/pack:overlap8";
	private static final String ImgOverlap16 = "examples/quadtree/pack:overlap16";
	private static final String ImgOverlap32 = "examples/quadtree/pack:overlap32";
	private static final String ImgOverlap64 = "examples/quadtree/pack:overlap64";
	
	private FlxGroup _walls;
	private FlxGroup _blocks;
	private FlxGroup _blocks2;
	private FlxGroup _blocks4;
	private FlxGroup _blocks6;
	private FlxGroup _blocks20;
	private FlxGroup _blocks30;
	private FlxGroup _nodes8;
	private FlxGroup _nodes16;
	private FlxGroup _nodes32;
	private FlxGroup _nodes64;
	
	private boolean _drawQuadNodes;
	
	@Override
	public void create()
	{
		FlxG.setBgColor(0xFFfafafa);
		FlxG.debug = true;
		FlxG.camera.setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT, true);
		
		// Walls
		add(_walls = new FlxGroup());
		// top
		_walls.add(new Wall(0, 0, WORLD_WIDTH, 1));
		// right
		_walls.add(new Wall(WORLD_WIDTH-1, 0, 1, WORLD_HEIGHT));
		// bottom
		_walls.add(new Wall(0, WORLD_HEIGHT-1, WORLD_WIDTH, 1));
		// left
		_walls.add(new Wall(0, 0, 1, WORLD_HEIGHT));
				
		// Moving blocks
		add(_blocks = new FlxGroup());
		createBlocks(100, Img2, 2, _blocks2 = new FlxGroup());
		createBlocks(75, Img4, 4, _blocks4 = new FlxGroup());
		createBlocks(50, Img6, 6, _blocks6 = new FlxGroup());
		createBlocks(25, Img20, 20, _blocks20 = new FlxGroup());
		createBlocks(5, Img30, 30, _blocks30 = new FlxGroup());
				
		// Background
		FlxSprite bg = new FlxSprite().loadGraphic(ImgBg, false, false, WORLD_WIDTH, WORLD_HEIGHT);
		bg.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		add(bg);
		
		// Overlap tiles
		add(_nodes8 = new FlxGroup());
		add(_nodes16 = new FlxGroup());
		add(_nodes32 = new FlxGroup());
		add(_nodes64 = new FlxGroup());
		createOverlapBlocks(ImgOverlap8,  8,  _nodes8);
		createOverlapBlocks(ImgOverlap16, 16, _nodes16);
		createOverlapBlocks(ImgOverlap32, 32, _nodes32);
		createOverlapBlocks(ImgOverlap64, 64, _nodes64);
		
		// Add toggle button
		FlxButton button = new FlxButton(FlxG.width-100, 0+20, "QuadNodes", new IFlxButton()
		{
			@Override
			public void callback()
			{
				_drawQuadNodes = !_drawQuadNodes;
			}
		});
		button.scrollFactor.x = button.scrollFactor.y = 0;
		add(button);
	}
	
	@Override
	public void update()
	{
		if(FlxG.keys.justPressed("P"))
			FlxG.paused = !FlxG.paused;
		if(FlxG.paused)
			return;
		
		super.update();
		FlxG.collide(_walls, _blocks);
		FlxG.overlap(_blocks, _blocks, onOverlapBlocks);
		if(_drawQuadNodes)
		{
			FlxG.overlap(_blocks2, _nodes8, onOverlapNodes);
			FlxG.overlap(_blocks4, _nodes8, onOverlapNodes);
			FlxG.overlap(_blocks6, _nodes16, onOverlapNodes);
			FlxG.overlap(_blocks20, _nodes32, onOverlapNodes);
			FlxG.overlap(_blocks30, _nodes64, onOverlapNodes);			
		}
		
		if(FlxG.mouse.justPressed())
		{
			_last.x = FlxG.mouse.screenX;
			_last.y = FlxG.mouse.screenY;
		}
		
		if(FlxG.mouse.pressed())
		{
			FlxG.camera.scroll.x -= FlxG.mouse.screenX - _last.x;
			FlxG.camera.scroll.y -= FlxG.mouse.screenY - _last.y;
			_last.x = FlxG.mouse.screenX;
			_last.y = FlxG.mouse.screenY;
		}
	}
	
	public void createBlocks(int amount, String img, int size, FlxGroup group)
	{
		float rx, ry;
		Block block;
		for(int i = 0; i < amount; i++)
		{
			rx = FlxG.random() * (WORLD_WIDTH - size);
			ry = FlxG.random() * (WORLD_HEIGHT - size);
			block = new Block(rx, ry, img, size);
			_blocks.add(block);
			group.add(block);
		}
	}
	
	public void createOverlapBlocks(String img, int size, FlxGroup group)
	{
		int row = FlxU.ceil((float)WORLD_WIDTH / size);
		int col = FlxU.ceil((float)WORLD_HEIGHT/ size);
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
				group.add(new Node(i * size, j * size, img));
		}
	}
	
	@Override
	public void destroy() 
	{
		super.destroy();
		_walls = null;
		_blocks = null;
		_blocks2 = null;
		_blocks4 = null;
		_blocks6 = null;
		_blocks20 = null;
		_blocks30 = null;
		_nodes8 = null;
		_nodes16 = null;
		_nodes32 = null;
		_nodes64 = null;
	}
	
	IFlxCollision onOverlapBlocks = new IFlxCollision()
	{			
		@Override
		public void callback(FlxObject block1, FlxObject block2)
		{
			((Block)block1).onOverlap();
			((Block)block2).onOverlap();
		}
	};
	
	IFlxCollision onOverlapNodes = new IFlxCollision()
	{			
		@Override
		public void callback(FlxObject block, FlxObject node)
		{
			((Node)node).onOverlap();
		}
	};
}

