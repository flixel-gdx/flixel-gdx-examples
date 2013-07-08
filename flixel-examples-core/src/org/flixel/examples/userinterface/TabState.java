package org.flixel.examples.userinterface;

import org.flixel.FlxG;
import org.flixel.FlxText;
import org.flixel.ui.FlxTab;
import org.flixel.ui.FlxTabGroup;
import org.flixel.ui.FlxUISkin;

/**
 *
 * @author Ka Wing Chin
 */
public class TabState extends Test
{
	private final String ImgTab = "examples/userinterface/holo_dark/tab.png";
	private final String ImgDivider = "examples/userinterface/holo_dark/tab_divider.png";
	
	@Override
	public void create()
	{
		super.create();
		FlxTabGroup tabGroup = new FlxTabGroup(0, 0);
//		tabGroup.align = tabGroup.ALIGN_VERTICAL;
		add(tabGroup);
		
		// Setup skin
		FlxUISkin skin = new FlxUISkin();
		skin.HIGHLIGHT = 1;
		skin.PRESSED = 2;
		skin.ACTIVE_NORMAL = 1;
		skin.setFormat(FntRobotoRegular, 14, 0xFFFFFF, "center");
		skin.labelOffset.y = 12;
		skin.setImage(ImgTab, 1, 48);
		
		FlxTab tab = createTab(skin, "TAB ONE");		
		tabGroup.addTab(tab);
		tabGroup.addTab(createTab(skin, "TAB TWO"));
		tabGroup.addTab(createTab(skin, "TAB THREE"));
		tabGroup.addTab(createTab(skin, "TAB FOUR"));
		tabGroup.addTab(createTab(skin, "TAB FIVE"));
		tabGroup.loadDividerSkin(ImgDivider, 1, 48);
		tabGroup.setDefault(2);

		tabGroup.addContent(new FlxText(10, 100, FlxG.width, "You can place any items in the tab!").setFormat(FntRobotoRegular, 18), 0);
		tabGroup.addContent(new FlxText(10, 100, FlxG.width, "You can also set the tabs vertically").setFormat(FntRobotoRegular, 18), 1);
		tabGroup.addContent(new FlxText(10, 100, FlxG.width, "And you can also give a width of the tab holder").setFormat(FntRobotoRegular, 18), 2);
		tabGroup.addContent(new FlxText(10, 100, FlxG.width, "The tabs automagically resize").setFormat(FntRobotoRegular, 18), 3);
		tabGroup.addContent(new FlxText(10, 100, FlxG.width, "Ain't it cool?!").setFormat(FntRobotoRegular, 18), 4);
	}
}

