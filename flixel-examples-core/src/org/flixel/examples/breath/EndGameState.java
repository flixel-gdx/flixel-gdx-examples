package org.flixel.examples.breath;

import org.flixel.*;

public class EndGameState extends FlxState {
    
    private String GardeniaFont = "examples/breath/data/gardenia.ttf";
    
    private MyText text;

    private FlxSprite bg;
    
    @Override
    public void create() {
        bg = new FlxSprite(0,0);
        bg.makeGraphic(FlxG.width, FlxG.height, 0xffffffff);
        
        text = new MyText(0, (FlxG.height / 2) - 32, FlxG.width, "jake elliott\n2010");
        text.setFormatExtended(GardeniaFont, 16, 0xff000000, "center", 0, -5);

        this.add(bg);
        this.add(text);
        
        FlxG.flash(0xff000000, 5);
    }
}