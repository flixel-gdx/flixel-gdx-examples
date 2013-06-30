package org.flixel.examples.userinterface;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.ui.FlxCheckBox;
import org.flixel.ui.FlxInputText;
import org.flixel.ui.FlxDialogBox;
import org.flixel.ui.FlxRadioButton;
import org.flixel.ui.FlxRadioButtonGroup;
import org.flixel.ui.FlxSwitch;
import org.flixel.ui.FlxUIGroup;
import org.flixel.ui.event.IFlxRadioButtonGroup;

/**
 *
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	public static final String FntRobotoRegular = "examples/userinterface/Roboto-Regular.ttf";
	
	private FlxUIGroup _checkBoxGroup;
	private FlxUIGroup _radioButtonGroup;
	
	private FlxCheckBox _checkBox1;
	private FlxCheckBox _checkBox2;
	private FlxCheckBox _checkBox3;
	
	private FlxRadioButtonGroup _radioGroup;
	private FlxRadioButton _radioButton1;
	private FlxRadioButton _radioButton2;
	private FlxRadioButton _radioButton3;

	private FlxInputText _inputText;
	private FlxDialogBox _dialog;

	private FlxSwitch _switch;

	@Override
	public void create()
	{
		FlxG.setBgColor(0xFF111111);
		
		add(_checkBoxGroup = new FlxUIGroup(10, 10));
		createCheckBox("A1", "Want cake?", _checkBoxGroup);
		createCheckBox("A2", "That cake was a lie", _checkBoxGroup);
		createCheckBox("A3", "No really?", _checkBoxGroup);
		
		
		add(_radioButtonGroup = new FlxUIGroup(220, 10));
		_radioGroup = new FlxRadioButtonGroup();
		_radioGroup.onChange = new IFlxRadioButtonGroup()
		{			
			@Override
			public void callback()
			{
				FlxG.log(_radioGroup.getSelectedLabel());
			}
		};
		
		createRadioButton("A", "Android", _radioGroup, _radioButtonGroup);
		createRadioButton("B", "Linux", _radioGroup, _radioButtonGroup);
		createRadioButton("C", "Apple", _radioGroup, _radioButtonGroup);
		_radioGroup.setCheck(1);
		
		add(_switch = new FlxSwitch(130, 150, "Turn the lights"));
		_switch.labelOffset.x = -10;
		_switch.label.setFormat(FntRobotoRegular, 18);
		
		
		
		_dialog = new FlxDialogBox(10, 280, 300, "DIALOG BOX", "Enter your message");
		_dialog.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		_dialog.textField.setFormat(FntRobotoRegular, 18);
		_dialog.setMaxLength(26);
		add(_dialog);
		
	}
	
	public FlxCheckBox createCheckBox(String ID, String label, FlxUIGroup group)
	{
		FlxCheckBox box = new FlxCheckBox(0, 0, ID, label);
		box.label.setFormat(FntRobotoRegular, 18);
		group.add(box);
		return box;
	}
	
	public FlxRadioButton createRadioButton(String ID, String label, FlxRadioButtonGroup radioGroup, FlxUIGroup group)
	{
		FlxRadioButton radio = new FlxRadioButton(0, 0, ID, radioGroup, label);
		radio.label.setFormat(FntRobotoRegular, 18);
		group.add(radio);
		return radio;
	}
}

