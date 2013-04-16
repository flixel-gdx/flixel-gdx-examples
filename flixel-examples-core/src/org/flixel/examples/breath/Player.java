package org.flixel.examples.breath;

import org.flixel.*;

public class Player extends FlxSprite {
    
    //private String PlayerImage = "examples/breath/data/pack:bird_player_image";
    
    private String NewPlayerImage = "examples/breath/data/pack:newplayer";
    
    private String GlowImage = "examples/breath/data/pack:glow-light";

    public boolean gravity_on = true;
    public boolean in_water = false;
    public boolean push_up = false;        
    private int _move_speed;

    public FlxSprite glow;
    public FlxSprite darkness;        

    public boolean won_game = false;
    
    public Player(float X, float Y, FlxSprite darkness) {
        super(X,Y);

        this.darkness = darkness;
        
        glow = new FlxSprite(X,Y,GlowImage);
        glow.scale = new FlxPoint(4,4);
        glow.setAlpha(1);
        glow.blend = "screen";
        
        this.loadGraphic(NewPlayerImage, true, true);

        maxVelocity.x = 140;
        maxVelocity.y = 140;

        _move_speed = 700;
        drag.x = 500;

        addAnimation("walk", new int[]{0,1,2,3}, 12);
        addAnimation("stopped", new int[]{9});
        addAnimation("jump", new int[]{2,3,4},2);
        addAnimation("mid-air",new int[]{4});
        
        width = 4;
        offset.x = 6;
    }

    @Override
    public void update() {
        if(!won_game) {
            // Physics/movement
            if(gravity_on) {
                acceleration.y = 420;
                drag.x = 500;
                drag.y = 0;
            } else {
                acceleration.y = 40;
                drag.x = drag.y = 300;
            }

            if(push_up) {
                acceleration.y = -40;
            }

            if(FlxG.keys.LEFT) {
                setFacing(LEFT);
                velocity.x -= _move_speed * FlxG.elapsed;
            } else if(FlxG.keys.RIGHT) {
                setFacing(RIGHT);
                velocity.x += _move_speed * FlxG.elapsed;                
            }

            if(!gravity_on) {
                if(FlxG.keys.UP) {
                    velocity.y -= _move_speed * FlxG.elapsed;
                } else if(FlxG.keys.DOWN) {
                    velocity.y += _move_speed * FlxG.elapsed;                
                }
            }
        }

        // Animation
        if(in_water) {
            setAlpha(0.7f);
        } else {
            setAlpha(1.0f);
        }

        if(velocity.x != 0 || velocity.y < 0) {
            play("walk");
        } else {
            play("stopped");
        }
        
        super.update();
    }

    public void drawGlow() {
    	/*
        FlxPoint firefly_point = new FlxPoint();
        
        getScreenXY(firefly_point);

        darkness.stamp(
            glow,
            (int)(firefly_point.x - (glow.width / 2)),
            (int)(firefly_point.y - (glow.height/ 2))
        );
        */
    	
        glow.x = (int)(x - ((glow.width) / 2f));
        glow.y = (int)(y - ((glow.height) / 2f));
        
        glow.draw();
    }
    
}