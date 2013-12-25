package org.flixel.examples.box2d;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.event.IFlxButton;
import org.flxbox2d.collision.shapes.B2FlxBox;
import org.flxbox2d.plugin.ExplosionPlugin;
import org.flxbox2d.plugin.ImplosionPlugin;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * 
 *
 * @author Ka Wing Chin
 */
public class TestExplosion extends Test
{
	private FlxButton _button;
	private ExplosionPlugin _explosion;
	private ImplosionPlugin _implosion;
	private boolean _isExplosion;

	@Override
	public void create()
	{		
		super.create();
		title.setText("Explosion & Implosion");
		info.setText("Click anywhere to cause an explosive or implosive force.");
		
		_explosion = new ExplosionPlugin();
		_implosion = new ImplosionPlugin();
		
		_isExplosion = true;
		
		// Fixture definition
		FixtureDef fd = new FixtureDef();
		fd.density = 1f;
		fd.friction = .5f;
		fd.restitution = .1f;
		
		// Create 4 stacks
		int i;
		for(i = 0; i < 10; i++)
		{
			add(new B2FlxBox(620/2f + FlxG.random() * .02f - .01f, 360 - 20 - i * 25, 20, 20).setFixtureDef(fd).setDraggable(true).create());
			add(new B2FlxBox(620/2f + 100, 360 - 20 - i * 25, 20, 20).setFixtureDef(fd).setDraggable(true).create());
			add(new B2FlxBox(620/2f + 200 + FlxG.random() * .02f - .01f, 360 - 20 - i * 25, 20, 20).setFixtureDef(fd).setDraggable(true).create());
		}
		
		_button = new FlxButton(10, 100, "Explosion ON", new IFlxButton()	
		{	
			@Override
			public void callback()
			{
				_isExplosion = !_isExplosion;
				if(_isExplosion)
					_button.label.setText("Explosion ON");
				else
					_button.label.setText("Implosion ON");
			}
		});
		add(_button);
	}
	
	@Override
	public void update()
	{
		if(FlxG.mouse.justPressed())
		{
			if(_isExplosion)
				_explosion.create(FlxG.mouse.x, FlxG.mouse.y, 200, 20);
			else
				_implosion.create(FlxG.mouse.x, FlxG.mouse.y, 200, 20);
		}
		super.update();
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		_button = null;
		_explosion = null;
		_implosion = null;
	}
}

