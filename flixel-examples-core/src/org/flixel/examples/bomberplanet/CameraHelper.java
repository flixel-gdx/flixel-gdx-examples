package org.flixel.examples.bomberplanet;

import org.flixel.*;

import com.badlogic.gdx.utils.Array;

public class CameraHelper extends FlxSprite
{
	protected Player _player;
	protected FlxPoint _target;

	public CameraHelper(Player PlayerRef)
	{
		super();
		_player = PlayerRef;
		_target = new FlxPoint();
		checkTarget();
		x = _target.x - width*0.5f;
		y = _target.y - height*0.5f;
		path = new FlxPath(new Array<FlxPoint>(new FlxPoint[]{new FlxPoint(),new FlxPoint()}));
	}

	@Override
	public void update()
	{
		preUpdate();

		if(pathSpeed == 0)
		{
			velocity.x = velocity.y = 0;
			checkTarget();
			if((x + width*0.5 != _target.x) || (y + height*0.5 != _target.y))
			{
				path.nodes.get(0).x = x + width*0.5f;
				path.nodes.get(0).y = y + height*0.5f;
				path.nodes.get(1).x = _target.x;
				path.nodes.get(1).y = _target.y;
				followPath(path,300);
			}
		}

		postUpdate();
	}

	protected void checkTarget()
	{
		int px = (int) _player.x;
		if(_player.velocity.x < 0)
			px += 16;
		int py = (int) _player.y;
		if(_player.velocity.y < 0)
			py += 16;
		_target.x = (int)(px/FlxG.width)*FlxG.width + FlxG.width*0.5f;
		_target.y = (int)(py/FlxG.height)*FlxG.height + FlxG.height*0.5f;
	}
}