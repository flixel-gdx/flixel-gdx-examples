package org.flixel.examples.breath;

import org.flixel.*;

public class StoryOverlay extends FlxGroup {
    
    private String GardeniaFont = "examples/breath/data/gardenia.ttf";
    
    private MyText text;

    // The number of seconds to hold the text before it starts to fade.
    private float text_lifespan = 2.0f;
    private float fade_timer;
    
    public StoryOverlay(int x, int y) {
        super();

        //this.x = x;
        //this.y = y;

        //this.scrollFactor.x = this.scrollFactor.y = 0;
        
        text = new MyText(x, y, 290, " ");
        text.scrollFactor.x  = text.scrollFactor.y = 0;
        text.setFormatExtended(GardeniaFont, 8, 0xffffffff, null, 0, -5);
        add(text);//, true);

        text.setAlpha(0);
    }

    public StoryOverlay(int x) {
        this(x, 0);
    }
    
    public StoryOverlay() {
        this(0, 0);
    }
    
    public void showText(String new_text) {
        fade_timer = text_lifespan;
        text.setText(new_text);
        text.setAlpha(1.0f);
    }

    @Override 
    public void update() {
        super.update();

        if(fade_timer > 0) {
            fade_timer -= FlxG.elapsed;
        } else {
            text.setAlpha(text.getAlpha() - FlxG.elapsed);
        }

        if(text.getAlpha() < 0) {
            text.setAlpha(0);
        }
    }
}