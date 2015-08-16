package org.flixel.examples.flappybalt;

import org.flixel.FlxG;
import org.flixel.FlxObject;
import org.flixel.FlxSave;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;

public class PlayState extends FlxState
{
	protected String ImgBG = "examples/flappybalt/pack:background";
	protected String ImgBounce = "examples/flappybalt/pack:bounce";
		
	static public final String SAVE_DATA = "FLAPPYBALT";
		
	public Player player;
		
	public FlxSprite bounceLeft;
	public FlxSprite bounceRight;
		
	public Paddle paddleLeft;
	public Paddle paddleRight;
		
	public FlxText scoreDisplay;
		
	@Override
	public void create()
	{
		FlxG.score = 0;
			
		add(new FlxSprite(0,0,ImgBG));
			
		//current score
		scoreDisplay = new FlxText(75,141,90);
		scoreDisplay.setColor(0xff4d4d59);
		scoreDisplay.setSize(24);
		add(scoreDisplay);
			
		//all-time high score
		int oldHighScore = loadScore();
		if(oldHighScore > 0)
			((FlxText)add(new FlxText(FlxG.width*0.5f - 20,16,40,String.valueOf(oldHighScore)))).setAlignment("center");

		bounceLeft = new FlxSprite(1,17).loadGraphic(ImgBounce,true,false,4,206);
		bounceLeft.addAnimation("flash",new int[]{1,0},8,false);
		add(bounceLeft);
		bounceRight = new FlxSprite(FlxG.width-5,17).loadGraphic(ImgBounce,true,false,4,206);
		bounceRight.addAnimation("flash",new int[]{1,0},8,false);
		add(bounceRight);
			
		paddleLeft = new Paddle(6,FlxObject.RIGHT);
		add(paddleLeft);
		paddleRight = new Paddle(FlxG.width-15,FlxObject.LEFT);
		add(paddleRight);
			
		player = new Player();
		add(player);
	}
		
	@Override
	public void update()
	{
		super.update();
			
		int edges = 14;
		if((player.y < edges) || (player.y + player.height > FlxG.height-edges) || player.overlaps(paddleLeft) || player.overlaps(paddleRight))
			player.kill();
		else if(player.x < 5)
		{
			player.x = 5;
			player.velocity.x = -player.velocity.x;
			player.setFacing(FlxObject.RIGHT);
			FlxG.score++;
			scoreDisplay.setText(String.valueOf(FlxG.score));
			bounceLeft.play("flash");
			paddleRight.randomize();
		}
		else if(player.x + player.width > FlxG.width - 5)
		{
			player.x = FlxG.width - player.width - 5;
			player.velocity.x = -player.velocity.x;
			player.setFacing(FlxObject.LEFT);
			FlxG.score++;
			scoreDisplay.setText(String.valueOf(FlxG.score));
			bounceRight.play("flash");
			paddleLeft.randomize();
		}
			
		if(FlxG.keys.justPressed("E") && (FlxG.keys.CONTROL || FlxG.keys.SHIFT || FlxG.keys.ALT))
		{
			clearSave();
			FlxG.resetState();
		}
	}
		
	//safely store a new high score into the saved session if possible
	static public void saveScore()
	{
		FlxSave save = new FlxSave();
		if(save.bind(SAVE_DATA))
		{
			if((save.data.get("score",Integer.class) == null) || (save.data.get("score",Integer.class) < FlxG.score))
				save.data.put("score",FlxG.score);
		}
	}
		
	//load data from the saved session (mostly used elsewhere)
	//returns the total points
	static public int loadScore()
	{
		FlxSave save = new FlxSave();
		if(save.bind(SAVE_DATA))
		{
			if((save.data != null) && (save.data.get("score",Integer.class) != null))
				return save.data.get("score",Integer.class);
		}
		return 0;
	}
		
	//wipe save data
	static public void clearSave()
	{
		FlxSave save = new FlxSave();
		if(save.bind(SAVE_DATA))
			save.erase();
	}
}
