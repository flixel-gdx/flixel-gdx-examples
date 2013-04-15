package org.flixel.examples.breath;

import org.flixel.*;

public class RestorePoint extends FlxSprite {
    
    private static String WallNoteImage = "examples/breath/data/pack:wall-note";

    public boolean note = true;
    
    public RestorePoint(int X,int Y) {
        // Snap the x & y values to the grid
        /*
        X -= X % World.TILE_SIZE;
        Y -= Y % World.TILE_SIZE;
        */
        
        super(X,Y,WallNoteImage);
    }
}