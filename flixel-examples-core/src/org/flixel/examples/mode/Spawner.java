package org.flixel.examples.mode;

import org.flixel.*;
import org.flixel.event.IFlxCamera;

public class Spawner extends FlxSprite
{
	private String ImgSpawner = "examples/mode/pack:spawner";
	private String SndExplode = "examples/mode/asplode.mp3";
	private String SndExplode2 = "examples/mode/menu_hit_2.mp3";
	private String SndHit = "examples/mode/hit.mp3";
	
	private float _timer;
	private FlxGroup _bots;
	private FlxGroup _botBullets;
	private FlxEmitter _botGibs;
	private FlxEmitter _gibs;
	private Player _player;
	private boolean _open;
	private FlxSound _sfxExplode;
	private FlxSound _sfxExplode2;
	private FlxSound _sfxHit;

	public Spawner(int X,int Y,FlxEmitter Gibs,FlxGroup Bots,FlxGroup BotBullets, FlxEmitter BotGibs,Player ThePlayer)
	{
		super(X,Y);
		loadGraphic(ImgSpawner,true);
		_gibs = Gibs;
		_bots = Bots;
		_botBullets = BotBullets;
		_botGibs = BotGibs;
		_player = ThePlayer;
		_timer = FlxG.random()*20;
		_open = false;
		health = 8;

		addAnimation("open", new int[]{1, 2, 3, 4, 5}, 40, false);
		addAnimation("close", new int[]{4, 3, 2, 1, 0}, 40, false);
		addAnimation("dead", new int[]{6});
		
		_sfxExplode = new FlxSound().loadEmbedded(SndExplode, false, false, FlxSound.SFX);
		_sfxExplode2 = new FlxSound().loadEmbedded(SndExplode2, false, false, FlxSound.SFX);
		_sfxHit = new FlxSound().loadEmbedded(SndHit, false, false, FlxSound.SFX);
	}

	@Override 
	public void destroy()
	{
		super.destroy();
		_bots = null;
		_botGibs = null;
		_botBullets = null;
		_gibs = null;
		_player = null;
	}

	@Override
	public void update()
	{
		_timer += FlxG.elapsed;
		int limit = 20;
		if(onScreen())
			limit = 4;
		if(_timer > limit)
		{
			_timer = 0;
			makeBot();
		}
		else if(_timer > limit - 0.35)
		{
			if(!_open)
			{
				_open = true;
				play("open");
			}
		}
		else if(_timer > 1)
		{
			if(_open)
			{
				play("close");
				_open = false;
			}
		}

		super.update();
	}

	@Override
	public void hurt(float Damage)
	{
		_sfxHit.play(true);
		flicker(0.2f);
		FlxG.score += 50;
		super.hurt(Damage);
	}

	@Override
	public void kill()
	{
		if(!alive)
			return;
		_sfxExplode.play(true);
		_sfxExplode2.play(true);
		super.kill();
		active = false;
		exists = true;
		setSolid(false);
		flicker(0);
		play("dead");
		FlxG.camera.shake(0.007f,0.25f);
		FlxG.camera.flash(0xffd8eba2,0.65f,new IFlxCamera(){@Override public void callback(){turnOffSlowMo();}});
		FlxG.timeScale = 0.35f;
		makeBot();
		_gibs.at(this);
		_gibs.start(true,3);
		FlxG.score += 1000;
	}

	protected void makeBot()
	{
		((Enemy) _bots.recycle(Enemy.class)).init((int) (x + width/2), (int) (y + height/2), _botBullets, _botGibs, _player);
	}

	protected void turnOffSlowMo()
	{
		FlxG.timeScale = 1.0f;
	}
}