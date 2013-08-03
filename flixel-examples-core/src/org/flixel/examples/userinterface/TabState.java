package org.flixel.examples.userinterface;

import org.flixel.FlxG;
import org.flixel.FlxText;
import org.flixel.ui.FlxTab;
import org.flixel.ui.FlxTabGroup;

/**
 *
 * @author Ka Wing Chin
 */
public class TabState extends Test
{	
	@Override
	public void create()
	{
		super.create();
		// Tab group that holds the tabs.
		FlxTabGroup tabGroup = new FlxTabGroup(0, 0);
		add(tabGroup);
				
		// Create bunch of tabs.
		tabGroup.addTab(new FlxTab(null, "TAB ONE"));
		tabGroup.addTab(new FlxTab(null, "TAB TWO"));
		tabGroup.addTab(new FlxTab(null, "TAB THREE"));
		tabGroup.addTab(new FlxTab(null, "TAB FOUR"));
		tabGroup.addTab(new FlxTab(null, "TAB FIVE"));
		tabGroup.loadDividerSkin();
		tabGroup.setDefault(0);

		//  Add content
		tabGroup.addContent(0, new FlxText(10, 100, FlxG.width-20, "You can place any items in the tab!").setFormat(FntRobotoRegular, 18));
		tabGroup.addContent(1, new FlxText(10, 100, FlxG.width-20, "You can also set the tabs vertically.").setFormat(FntRobotoRegular, 18));
		tabGroup.addContent(2, new FlxText(10, 100, FlxG.width-20, "And you can also give width of the tab holder.").setFormat(FntRobotoRegular, 18));
		tabGroup.addContent(3, new FlxText(10, 100, FlxG.width-20, "The tabs automagically resize.").setFormat(FntRobotoRegular, 18));
		tabGroup.addContent(4, new FlxText(10, 100, FlxG.width-20, "Ain't it cool?!").setFormat(FntRobotoRegular, 18));
	}
}

