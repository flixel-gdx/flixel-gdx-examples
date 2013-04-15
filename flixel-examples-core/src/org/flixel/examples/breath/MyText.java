package org.flixel.examples.breath;

import org.flixel.*;

public class MyText extends FlxText {

	public MyText(float X, float Y, int Width, String Text) {
        super(X,Y,Width,Text);
    }
	
	public MyText(float X, float Y, int Width) {
        this(X,Y,Width,null);
    }
    
	public FlxText setFormatExtended(String Font,float Size,int Color,String Alignment,int ShadowColor,int leading) {
		setFormat(Font, Size, Color, Alignment, ShadowColor);
		/*if(Font == null)
			Font = "";
		var tf:TextFormat = dtfCopy();
		tf.font = Font;
		tf.size = Size;
		tf.color = Color;
		tf.align = Alignment;
        tf.leading = leading;
		_tf.defaultTextFormat = tf;
		_tf.setTextFormat(tf);
		_shadow = ShadowColor;
		_regen = true;
		calcFrame();*/
		return this;
	}
	
	public FlxText setFormatExtended(String Font,float Size,int Color,String Alignment,int ShadowColor) {
		return setFormatExtended(Font, Size, Color, Alignment, ShadowColor, 0);
	}
	
	public FlxText setFormatExtended(String Font,float Size,int Color,String Alignment) {
		return setFormatExtended(Font, Size, Color, Alignment, 0, 0);
	}
	
	public FlxText setFormatExtended(String Font,float Size,int Color) {
		return setFormatExtended(Font, Size, Color, null, 0, 0);
	}
	
	public FlxText setFormatExtended(String Font,float Size) {
		return setFormatExtended(Font, Size, 0xffffff, null, 0, 0);
	}
	
	public FlxText setFormatExtended(String Font) {
		return setFormatExtended(Font, 8, 0xffffff, null, 0, 0);
	}
	
	public FlxText setFormatExtended() {
		return setFormatExtended(null, 8, 0xffffff, null, 0, 0);
	}
}