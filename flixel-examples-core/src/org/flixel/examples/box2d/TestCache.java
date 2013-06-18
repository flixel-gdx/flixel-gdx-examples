package org.flixel.examples.box2d;


/**
 *
 * @author Ka Wing Chin
 */
public class TestCache extends Test
{
	@Override
	public void create()
	{		
		super.create();
		title.setText("Cache");
		info.setText("Bodies and joints suvives on state change.");
		
		// Box shape		
		add(Cache.a);
		add(Cache.b);
		Cache.a.setActive(active);
		Cache.b.setActive(active);
		add(Cache.g);
	}
	
	@Override
	public void destroy()
	{
		Cache.a.setActive(false);
		Cache.b.setActive(false);
		remove(Cache.g);
		super.destroy();
	}
}

