package org.flixel.examples.userinterface;

import org.flixel.ui.FlxCheckBox;
import org.flixel.ui.FlxUIGroup;


/**
 *
 * @author Ka Wing Chin
 */
public class CheckBoxState extends Test
{
	
	private FlxUIGroup _checkBoxGroup;

	@Override
	public void create()
	{
		super.create();
		add(_checkBoxGroup = new FlxUIGroup(10, 20, "CHECKBOXES"));
		_checkBoxGroup.label.setFormat(FntRobotoRegular, 14,0x0099CC);
		createCheckBox("A1", "Unchecked", _checkBoxGroup);
		
		FlxCheckBox box = createCheckBox("A2", "Checked", _checkBoxGroup);
		box.setActive(true);
		
		box = createCheckBox("A3", "Disabled", _checkBoxGroup);
		box.setEnable(false);
		
		box = createCheckBox("A4", "Disabled Checked", _checkBoxGroup);
		box.setEnable(false);
		box.setActive(true);
	}
	
	public FlxCheckBox createCheckBox(String ID, String label, FlxUIGroup group)
	{
		FlxCheckBox box = new FlxCheckBox(0, 0, ID, label);
		box.label.setFormat(FntRobotoRegular, 18);
		group.add(box);
		return box;
	}

}

